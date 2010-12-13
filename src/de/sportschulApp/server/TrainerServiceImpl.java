package de.sportschulApp.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.sportschulApp.client.services.TrainerService;
import de.sportschulApp.server.databanker.DataBankerEvent;
import de.sportschulApp.server.databanker.DataBankerMember;
import de.sportschulApp.shared.Event;
import de.sportschulApp.shared.EventParticipant;
import de.sportschulApp.shared.Member;

@SuppressWarnings("serial")
public class TrainerServiceImpl extends RemoteServiceServlet implements
TrainerService {

	DataBankerMember dbm = new DataBankerMember();
	DataBankerEvent dbe = new DataBankerEvent();

	public Member getMemberByBarcodeID(int barcodeID) {
		return dbm.getMember(barcodeID);
	}

	public String getNote(int barcodeID) {
		return dbm.getNote(barcodeID);
	}

	public int getTrainingspresence(int memberID, int month, int year) {
		return dbm.getTrainingsPresenceInt(memberID, month, year);
	}

	public String setNote(int barcodeID, String note) {
		dbm.setNote(barcodeID, note);
		return null;
	}

	public String setTrainingsPresence(int barcodeID, int day, int month,
			int year) {
		dbm.setTrainingsPresence(barcodeID, day, month, year);
		return null;
	}

	public ArrayList<Event> getEventList() {
		return (new AdminServiceImpl().getEventList());
	}

	public void startEvent(int eventID, String user) {
		dbe.startEvent(eventID, user);
	}

	public ArrayList<EventParticipant> getEventParticipants(int eventID) {
		return (new AdminServiceImpl().getEventParticipants(eventID));
	}

}
