package de.sportschulApp.client.view.admin;

import java.util.ArrayList;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

import de.sportschulApp.client.event.ShowMemberEvent;
import de.sportschulApp.client.presenter.admin.ListEventParticipantsPresenter;
import de.sportschulApp.client.view.localization.LocalizationConstants;
import de.sportschulApp.shared.Event;
import de.sportschulApp.shared.EventParticipant;

@SuppressWarnings({ "unchecked", "deprecation" })
public class ListEventParticipantsView extends Composite implements ListEventParticipantsPresenter.Display {


	private CellTable<EventParticipant> cellTable = new CellTable<EventParticipant>();
	private HandlerManager eventBus;
	private ListDataProvider<EventParticipant> ldp;
	private ArrayList<EventParticipant> listData = new ArrayList<EventParticipant>();
	private Label submitButton;
	private VerticalPanel wrapper = new VerticalPanel();
	private LocalizationConstants constants;
	private Column<EventParticipant, Boolean> participationColumn;
	private Column<EventParticipant, Boolean> paidColumn;
	private Event event;
	private Label eventName = new Label();

	public ListEventParticipantsView(HandlerManager eventBus, LocalizationConstants constants) {
		this.eventBus = eventBus;
		this.constants = constants;
		
		wrapper.addStyleName("listWrapper");
		initWidget(wrapper);

		wrapper.add(createHeadPanel());
	}

	@Override
	public Widget asWidget() {
		return this;
	}


	public VerticalPanel createHeadPanel() {
		VerticalPanel listEventParticipantsHeader = new VerticalPanel();
		listEventParticipantsHeader.addStyleName("formWrapper");
		listEventParticipantsHeader.setWidth("100%");

		HorizontalPanel formHeader = new HorizontalPanel();
		formHeader.addStyleName("formHeader");
		formHeader.add(eventName);

		listEventParticipantsHeader.add(formHeader);

		return listEventParticipantsHeader;
	}


	public VerticalPanel createMemberListTable() {
		cellTable = new CellTable<EventParticipant>();

		ldp = new ListDataProvider<EventParticipant>();

		VerticalPanel tableWrapper = new VerticalPanel();
		ldp.setList(listData);
		ldp.refresh();

		cellTable.setPageSize(1000);
		cellTable.setWidth("700px");

		ldp.addDataDisplay(cellTable);



		Column<EventParticipant, String> pictureColumn = new Column<EventParticipant, String>(new ImageCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getPicUrl();
			}
		};

		Column<EventParticipant, String> barcodeColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getBarcodeID();
			}
		};

		Column<EventParticipant, String> forenameColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getForename();
			}
		};

		Column<EventParticipant, String> surnameColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getSurname();
			}
		};
		
		Column<EventParticipant, String> passedColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getPassed();
			}
		};

		Column<EventParticipant, String> noteColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getNote();
			}
		};

		Column<EventParticipant, String> participationColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getParticipant();
			}
		};


		Column<EventParticipant, String> paidColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getPaid();
			}
		};
		
		Column<EventParticipant, String> attendedColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getAttended();
			}
		};	
		
		Column<EventParticipant, String> gradeAfterExamColumn = new Column<EventParticipant, String>(new TextCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return object.getGradeAfterExam();
			}
		};	

		Column<EventParticipant, String> showMemberColumn = new Column<EventParticipant, String>(
				new ButtonCell()) {
			@Override
			public String getValue(EventParticipant object) {
				return "Klick";
			}
		};

		showMemberColumn.setFieldUpdater(new FieldUpdater<EventParticipant, String>() {
			public void update(int index, EventParticipant object, String value) {
				eventBus.fireEvent(new ShowMemberEvent(Integer.valueOf(object.getBarcodeID())));
			}
		});

		cellTable.addColumn(pictureColumn, "Bild");
		cellTable.addColumn(barcodeColumn, "Barcode");
		cellTable.addColumn(forenameColumn, "Vorname");
		cellTable.addColumn(surnameColumn, "Nachname");
		cellTable.addColumn(participationColumn, "Teilnahme");
		cellTable.addColumn(attendedColumn, "Anwesend");
		cellTable.addColumn(paidColumn, "Bezahlt?");
		cellTable.addColumn(passedColumn, "Bestanden?");
		cellTable.addColumn(noteColumn, "Notiz");
		cellTable.addColumn(gradeAfterExamColumn, "Neuer Gurt");
		cellTable.addColumn(showMemberColumn, "Mitglied anzeigen");

		tableWrapper.add(cellTable);

		return tableWrapper;
	}

	public CellTable getCellTable() {
		return cellTable;
	}

	public void setMemberList(ArrayList<EventParticipant> memberList) {
		listData = memberList;
		cellTable.removeFromParent();
		wrapper.add(createMemberListTable());
	}
	
	public void setEvent(Event event) {
		this.event = event;
		this.eventName.setText("Teilnehmer für das Event '" + event.getName() + "' anzeigen");
	}
}
