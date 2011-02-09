package de.sportschulApp.client.presenter;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import de.sportschulApp.client.CookieManager;
import de.sportschulApp.client.presenter.Presenter;
import de.sportschulApp.client.services.LoginService;
import de.sportschulApp.client.services.TrainerService;
import de.sportschulApp.client.services.TrainerServiceAsync;
import de.sportschulApp.client.view.localization.LocalizationConstants;
import de.sportschulApp.shared.Event;
import de.sportschulApp.shared.EventParticipant;

public class PrintCertificatesPresenter implements Presenter {

	public interface Display {
		Widget asWidget();
		void setMemberList(ArrayList<EventParticipant> memberList);
		CellTable<EventParticipant> getCellTable();
		Column<EventParticipant, Boolean> getAttendedColumn();
		ListDataProvider<EventParticipant> getListProvider();
		void setEvent(Event event);
		HasClickHandlers getPrintButton();
		HasClickHandlers getSelectAllButton();
		HasClickHandlers getDeselectAllButton();
		HasClickHandlers getEndButton();
		TextBox getLocationTextBox();
		TextBox getDateTextBox();
		TextBox getExaminersTextBox();
		VerticalPanel getEvenWrapper();
		void newPrintMemberEntry(String location, String date, String examiners, String forename, String surename,
				String gradeAfterExam);
	}

	private final Display display;
	private String eventID;
	private DialogBox participantEditor;
	private LocalizationConstants constants;
	private VerticalPanel navigationContainer;
	private ArrayList<EventParticipant> allParticipants = new ArrayList<EventParticipant>();
	private TrainerServiceAsync rpcService = GWT.create(TrainerService.class);
	
	
	public PrintCertificatesPresenter(HandlerManager eventBus, Display display, String eventID, LocalizationConstants constants, VerticalPanel navigationContainer) {
		this.display = display;
		this.eventID = eventID;
		this.navigationContainer = navigationContainer;
		bind();
		fetchEventData();
		fetchListData();
	}

	private void bind() {
		display.getPrintButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String location = display.getLocationTextBox().getText();
				String date = display.getDateTextBox().getText();
				String examiners = display.getExaminersTextBox().getText();
				
				navigationContainer.setStyleName("printLayoutDisplay");
				display.getEvenWrapper().clear();
				display.getEvenWrapper().setStyleName("printWrapper");
				
				ArrayList<EventParticipant> participantsToPrint = new ArrayList<EventParticipant>();
				for (int i = 0; i < display.getListProvider().getList().size(); i++) {
					if (display.getListProvider().getList().get(i).getPassed().equals("Print")) {
						participantsToPrint.add(display.getListProvider().getList().get(i));
						display.newPrintMemberEntry(location, date, examiners, display.getListProvider().getList().get(i).getForename(), display.getListProvider().getList().get(i).getSurname(), display.getListProvider().getList().get(i).getGradeAfterExam());

					}
				}			
				
				


			}
		});
		
		display.getSelectAllButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < display.getListProvider().getList().size(); i++) {
					display.getListProvider().getList().get(i).setPassed("Print");
				}
				display.getCellTable().redraw();
			}
		});
		
		display.getDeselectAllButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int i = 0; i < display.getListProvider().getList().size(); i++) {
					display.getListProvider().getList().get(i).setPassed("NoPrint");
				}
				display.getCellTable().redraw();
			}
		});
		
		display.getEndButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (Window.confirm("Möchten sie das Drucken der Urkunden vorzeitig beenden? Die Urkunden können immer im Adminbereich gedruckt werden")) {
					if (Cookies.getCookie("SportschuleUserRight").equals("admin")) {
						History.newItem("adminEventsShowEvents");
					}
					
					if (Cookies.getCookie("SportschuleUserRight").equals("trainer")) {
						History.newItem("trainerNewEvent");
					}
				}
			}
		});
	}
	
	public void fetchEventData() {
		rpcService.getEventByEventID(Integer.valueOf(eventID), new AsyncCallback<Event>() {
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Abrufen der Eventinformationen.");
			}
			public void onSuccess(Event result) {
				display.setEvent(result);
			}
		});
	}
	
	public void fetchListData() {
		rpcService.getEventParticipants(Integer.valueOf(eventID) , new AsyncCallback<ArrayList<EventParticipant>>() {
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim abrufen der Teilnehmerliste");
			}
			public void onSuccess(ArrayList<EventParticipant> result) {
				allParticipants.addAll(result);
				ArrayList<EventParticipant> participants = new ArrayList<EventParticipant>();
				for (int i = 0; i < result.size(); i++) {
					if (result.get(i).getParticipant().equals("Ja") && result.get(i).getPassed().equals("Ja")) {
						participants.add(result.get(i));
					}
				}
				display.setMemberList(participants);
			}
		});
	}
	

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
