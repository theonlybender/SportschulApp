package de.sportschulApp.client.view;

import java.util.ArrayList;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import de.sportschulApp.client.presenter.PrintCertificatesPresenter;
import de.sportschulApp.client.view.localization.LocalizationConstants;
import de.sportschulApp.shared.Event;
import de.sportschulApp.shared.EventParticipant;

public class PrintCertificatesView extends Composite implements
		PrintCertificatesPresenter.Display {

	private VerticalPanel newEventWrapper;
	private LocalizationConstants constants;
	private ListDataProvider<EventParticipant> ldp;
	private ArrayList<EventParticipant> listData = new ArrayList<EventParticipant>();
	private CellTable<EventParticipant> cellTable = new CellTable<EventParticipant>();
	private TextBox barcodeTextBox;
	private Column<EventParticipant, Boolean> attendedColumn;
	private Label titleBar = new Label();
	private TextBox locationTextBox = new TextBox();
	private TextBox dateTextBox = new TextBox();
	private TextBox examinersTextBox = new TextBox();
	private Label printButton;
	private Label selectAllButton;
	private Label deselectAllButton;
	private Label endButton;

	public class PrintLayout {
		public PrintLayout(String location, String date, String examiners,
				String forename, String surename, String gradeAfterExam) {
			newEventWrapper.add(newPrintLayout(location, date, examiners,
					forename, surename, gradeAfterExam));
		}

		public AbsolutePanel newPrintLayout(String location, String date,
				String examiners, String forename, String surname,
				String gradeAfterExam) {
			AbsolutePanel printMemberEntry = new AbsolutePanel();

			Label nameLabel = new Label(forename + " " + surname);
			nameLabel.setStyleName("printNameLabel");
			printMemberEntry.add(nameLabel);

			Label dateLabel = new Label(date.substring(4));
			dateLabel.setStyleName("printDateLabel");
			printMemberEntry.add(dateLabel);

			Label locationLabel = new Label(location+",");
			locationLabel.setStyleName("printLocationLabel");
			printMemberEntry.add(locationLabel);

			Label gradeAfterExamLabel = new Label(gradeAfterExam);
			gradeAfterExamLabel.setStyleName("printGradeAfterExamLabel");
			printMemberEntry.add(gradeAfterExamLabel);

			printMemberEntry.setStyleName("printMemberEntry");

			return printMemberEntry;
		}

	}

	public PrintCertificatesView(LocalizationConstants constants) {
		this.constants = constants;

		newEventWrapper = new VerticalPanel();
		newEventWrapper.addStyleName("newEventWrapper");
		initWidget(newEventWrapper);

		VerticalPanel header = new VerticalPanel();
		header.addStyleName("formWrapper");
		header.setWidth("100%");

		HorizontalPanel formHeader = new HorizontalPanel();
		formHeader.addStyleName("formHeader");
		formHeader.add(titleBar);

		locationTextBox.addStyleName("formInput");
		dateTextBox.addStyleName("formInput");
		examinersTextBox.addStyleName("formInput");

		FlexTable eventFormPanel = new FlexTable();
		eventFormPanel.setCellSpacing(10);
		eventFormPanel.setWidget(0, 0, new Label("Ort: "));
		eventFormPanel.setWidget(0, 1, locationTextBox);
		eventFormPanel.setWidget(1, 0, new Label("Datum: "));
		eventFormPanel.setWidget(1, 1, dateTextBox);
		eventFormPanel.setWidget(2, 0, new Label("Prüfer: "));
		eventFormPanel.setWidget(2, 1, examinersTextBox);

		Label printInfo = new Label(
				"Es werden nur Mitglieder angezeigt, die die Prüfung bestanden haben.");
		Label printInfo2 = new Label(
				"Diese Informationen werden auf die Urkunden gedruckt: ");
		printInfo.addStyleName("printInfo");
		printInfo2.addStyleName("printInfo");

		HorizontalPanel buttonPanel = new HorizontalPanel();

		printButton = new Label("Urkunden Drucken");
		printButton.addStyleName("clickableLabel");
		printButton.addStyleName("printInfo");

		selectAllButton = new Label("Alle markieren");
		selectAllButton.addStyleName("clickableLabel");
		selectAllButton.addStyleName("printInfo");

		deselectAllButton = new Label("Alle Markierungen aufheben");
		deselectAllButton.addStyleName("clickableLabel");
		deselectAllButton.addStyleName("printInfo");

		endButton = new Label("Beenden");
		endButton.addStyleName("clickableLabel");
		endButton.addStyleName("printInfo");

		buttonPanel.add(printButton);
		buttonPanel.add(selectAllButton);
		buttonPanel.add(deselectAllButton);
		buttonPanel.add(endButton);

		header.add(formHeader);
		header.add(printInfo);
		header.add(printInfo2);
		header.add(eventFormPanel);
		header.add(buttonPanel);

		newEventWrapper.add(header);
		newEventWrapper.add(buildParticipantsList());

	}

	public VerticalPanel buildParticipantsList() {
		VerticalPanel tableWrapper = new VerticalPanel();

		cellTable = new CellTable<EventParticipant>();
		cellTable.setPageSize(1000);
		cellTable.setWidth("700px");

		ldp = new ListDataProvider<EventParticipant>();
		ldp.setList(listData);
		ldp.refresh();
		ldp.addDataDisplay(cellTable);

		Column<EventParticipant, String> pictureColumn = new Column<EventParticipant, String>(
				new ImageCell()) {
			public String getValue(EventParticipant object) {
				return object.getPicUrl();
			}
		};

		Column<EventParticipant, String> barcodeColumn = new Column<EventParticipant, String>(
				new TextCell()) {
			public String getValue(EventParticipant object) {
				return object.getBarcodeID();
			}
		};

		Column<EventParticipant, String> forenameColumn = new Column<EventParticipant, String>(
				new TextCell()) {
			public String getValue(EventParticipant object) {
				return object.getForename();
			}
		};

		Column<EventParticipant, String> surnameColumn = new Column<EventParticipant, String>(
				new TextCell()) {
			public String getValue(EventParticipant object) {
				return object.getSurname();
			}
		};

		Column<EventParticipant, String> gradeAfterExamColumn = new Column<EventParticipant, String>(
				new TextCell()) {
			public String getValue(EventParticipant object) {
				return object.getGradeAfterExam();
			}
		};

		Column<EventParticipant, Boolean> printColumn = new Column<EventParticipant, Boolean>(
				new CheckboxCell(true)) {
			public Boolean getValue(EventParticipant object) {
				if (object.getPassed().equals("Print")) {
					return true;
				} else {
					object.setPassed("NoPrint");
					return false;
				}
			}
		};

		printColumn
				.setFieldUpdater(new FieldUpdater<EventParticipant, Boolean>() {
					public void update(int index, EventParticipant object,
							Boolean value) {
						if (value == true) {
							object.setPassed("Print");
						} else {
							object.setPassed("NoPrint");
						}
						cellTable.redraw();
					}
				});

		cellTable.addColumn(pictureColumn, "Bild");
		cellTable.addColumn(barcodeColumn, "Barcode");
		cellTable.addColumn(forenameColumn, "Vorname");
		cellTable.addColumn(surnameColumn, "Nachname");
		cellTable.addColumn(gradeAfterExamColumn, "Neuer Gurt");
		cellTable.addColumn(printColumn, "Drucken?");

		tableWrapper.add(cellTable);

		return tableWrapper;
	}

	public Widget asWidget() {
		return this;
	}

	public void setMemberList(ArrayList<EventParticipant> memberList) {
		ldp.setList(memberList);

		for (int i = 0; i < ldp.getList().size(); i++) {
			ldp.getList().get(i).setPassed("Print");
		}
		cellTable.redraw();
	}

	public TextBox getBarcodeTextBox() {
		return barcodeTextBox;
	}

	public ListDataProvider<EventParticipant> getListProvider() {
		return ldp;
	}

	public CellTable<EventParticipant> getCellTable() {
		return cellTable;
	}

	public Column<EventParticipant, Boolean> getAttendedColumn() {
		return attendedColumn;
	}

	public void setEvent(Event event) {
		this.titleBar.setText("Urkunden für '" + event.getName() + "' ("
				+ event.getDate() + " - Kosten: " + event.getCosts() + ")");
		this.locationTextBox.setText(event.getLocation());
		this.dateTextBox.setText(event.getDate());

		String examiners = new String();
		for (int i = 0; i < event.getExaminers().size(); i++) {
			examiners = examiners + event.getExaminers().get(i) + ", ";
		}
		this.examinersTextBox.setText(examiners.substring(0,
				examiners.length() - 2));
	}

	public HasClickHandlers getPrintButton() {
		return this.printButton;
	}

	public HasClickHandlers getSelectAllButton() {
		return this.selectAllButton;
	}

	public HasClickHandlers getDeselectAllButton() {
		return this.deselectAllButton;
	}

	public HasClickHandlers getEndButton() {
		return this.endButton;
	}

	public TextBox getLocationTextBox() {
		return this.locationTextBox;
	}

	public TextBox getDateTextBox() {
		return this.dateTextBox;
	}

	public TextBox getExaminersTextBox() {
		return this.examinersTextBox;
	}

	public VerticalPanel getEvenWrapper() {
		return newEventWrapper;
	}

	public void newPrintMemberEntry(String location, String date,
			String examiners, String forename, String surename,
			String gradeAfterExam) {
		new PrintLayout(location, date, examiners, forename, surename,
				gradeAfterExam);
	}
}
