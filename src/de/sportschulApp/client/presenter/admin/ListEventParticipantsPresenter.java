package de.sportschulApp.client.presenter.admin;

import java.util.ArrayList;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.sportschulApp.client.presenter.Presenter;
import de.sportschulApp.client.services.AdminServiceAsync;
import de.sportschulApp.client.view.localization.LocalizationConstants;
import de.sportschulApp.shared.Event;
import de.sportschulApp.shared.EventParticipant;


@SuppressWarnings({ "unused", "deprecation" })
public class ListEventParticipantsPresenter implements Presenter{
	public interface Display{
		Widget asWidget();
		CellTable<EventParticipant> getCellTable();
		void setMemberList(ArrayList<EventParticipant> memberList);
		void setEvent(Event event);
	}

	private final Display display;
	private String eventID;
	private final AdminServiceAsync rpcService;
	private LocalizationConstants constants;

	public ListEventParticipantsPresenter(AdminServiceAsync rpcService, HandlerManager eventBus, Display display, String eventID, LocalizationConstants constants) {
		this.display = display;
		this.rpcService = rpcService;
		this.eventID = eventID;
		this.constants = constants;
		bind();
		System.out.println("jau");
		fetchEventData();
		fetchListData();
	}

	private void bind() {

	}
	

	public void fetchListData() {
		rpcService.getEventParticipants(Integer.valueOf(eventID) , new AsyncCallback<ArrayList<EventParticipant>>() {
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim abrufen der Teilnehmerliste");
			}
			public void onSuccess(ArrayList<EventParticipant> result) {
				ArrayList<EventParticipant> participants = new ArrayList<EventParticipant>();
				
				for (int i = 0; i < result.size(); i++) {
					if (result.get(i).getParticipant().equals("Ja")) {
						participants.add(result.get(i));
					}
				}
				
				display.setMemberList(participants);

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

	public Display getDisplay(){
		return display;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
}
