package de.sportschulApp.client.view.admin;

import java.util.ArrayList;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

import de.sportschulApp.client.presenter.admin.ListEventPresenter;
import de.sportschulApp.shared.Event;

@SuppressWarnings("unchecked")
public class ListEventView extends Composite implements ListEventPresenter.Display {


	private CellTable<Event> upcomingEventsCellTable = new CellTable<Event>();
	private CellTable<Event> pastEventsCellTable = new CellTable<Event>();
	private ListDataProvider<Event> ldpUpcoming = new ListDataProvider<Event>();
	private ListDataProvider<Event> ldpPast = new ListDataProvider<Event>();
	private VerticalPanel wrapper = new VerticalPanel();

	public ListEventView() {
		wrapper.addStyleName("listWrapper");
		initWidget(wrapper);
		
		wrapper.setSpacing(15);
		
		wrapper.add(createHeadUpcomingPanel());
		wrapper.add(createUpcomingEventListTable());
		wrapper.add(createHeadPastPanel());
		wrapper.add(createPastEventListTable());
	}
	
	public VerticalPanel createHeadUpcomingPanel() {
		VerticalPanel listEventHeader = new VerticalPanel();
		listEventHeader.addStyleName("formWrapper");
		listEventHeader.setWidth("100%");

		HorizontalPanel formHeader = new HorizontalPanel();
		formHeader.addStyleName("formHeader");
		formHeader.add(new Label("Anstehende Events"));

		listEventHeader.add(formHeader);

		return listEventHeader;
	}
	
	public VerticalPanel createHeadPastPanel() {
		VerticalPanel listEventHeader = new VerticalPanel();
		listEventHeader.addStyleName("formWrapper");
		listEventHeader.setWidth("100%");

		HorizontalPanel formHeader = new HorizontalPanel();
		formHeader.addStyleName("formHeader");
		formHeader.add(new Label("Vergangene Events"));

		listEventHeader.add(formHeader);

		return listEventHeader;
	}


	@Override
	public Widget asWidget() {
		return this;
	}


	public VerticalPanel createUpcomingEventListTable() {
		upcomingEventsCellTable = new CellTable<Event>();

		VerticalPanel tableWrapper = new VerticalPanel();

		upcomingEventsCellTable.setPageSize(1000);
		upcomingEventsCellTable.setWidth("700px");
		
		upcomingEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getName();
			}
		}, "Name");
		
		upcomingEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getType();
			}
		}, "Typ");

		upcomingEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getCosts();
			}
		}, "Kosten");

		upcomingEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getDate().toString();
			}
		}, "Datum");

		upcomingEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getStartTime() + " - " + event.getEndTime() + " Uhr";
			}
		}, "Uhrzeit");

		upcomingEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getLocation();
			}
		}, "Ort");

		tableWrapper.add(upcomingEventsCellTable);

		return tableWrapper;
	}
	
	public VerticalPanel createPastEventListTable() {
		pastEventsCellTable = new CellTable<Event>();

		VerticalPanel tableWrapper = new VerticalPanel();

		pastEventsCellTable.setPageSize(1000);
		pastEventsCellTable.setWidth("700px");
		
		pastEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getName();
			}
		}, "Name");
		
		pastEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getType();
			}
		}, "Typ");

		pastEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getCosts();
			}
		}, "Kosten");

		pastEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getDate().toString();
			}
		}, "Datum");

		pastEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getStartTime() + " - " + event.getEndTime() + " Uhr";
			}
		}, "Uhrzeit");

		pastEventsCellTable.addColumn(new TextColumn<Event>() {
			public String getValue(Event event) {
				return event.getLocation();
			}
		}, "Ort");

		tableWrapper.add(pastEventsCellTable);

		return tableWrapper;
	}

	public void setListData(ArrayList<Event> listData) {
		ArrayList<Event> upcomingEvents = new ArrayList<Event>();
		ArrayList<Event> pastEvents = new ArrayList<Event>();
		
		for (int i = 0; i < listData.size(); i++) {
			if (listData.get(i).getHappened().equals("Nein") || listData.get(i).getHappened().equals("Pause")) {
				upcomingEvents.add(listData.get(i));
			}
			if (listData.get(i).getHappened().equals("Beendet")) {
				pastEvents.add(listData.get(i));
			}
		}
		
		ldpUpcoming.setList(upcomingEvents);
		ldpUpcoming.addDataDisplay(upcomingEventsCellTable);
		ldpPast.setList(pastEvents);
		ldpPast.addDataDisplay(pastEventsCellTable);
	}

	public void setSelectionModel(SingleSelectionModel selectionModel) {
		upcomingEventsCellTable.setSelectionModel(selectionModel);
		pastEventsCellTable.setSelectionModel(selectionModel);
	}
}
