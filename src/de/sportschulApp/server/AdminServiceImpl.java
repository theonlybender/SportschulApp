package de.sportschulApp.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.sportschulApp.client.services.AdminService;
import de.sportschulApp.server.databanker.DataBankerCourse;
import de.sportschulApp.server.databanker.DataBankerEvent;
import de.sportschulApp.server.databanker.DataBankerMember;
import de.sportschulApp.server.databanker.DataBankerUser;
import de.sportschulApp.server.dtaus.CSatz;
import de.sportschulApp.server.dtaus.DtausDateiWriter;
import de.sportschulApp.shared.BankAccount;
import de.sportschulApp.shared.Belt;
import de.sportschulApp.shared.Course;
import de.sportschulApp.shared.CourseTariff;
import de.sportschulApp.shared.Event;
import de.sportschulApp.shared.EventParticipant;
import de.sportschulApp.shared.Member;
import de.sportschulApp.shared.User;

@SuppressWarnings("serial")
public class AdminServiceImpl extends RemoteServiceServlet implements
		AdminService {

	DataBankerCourse dbc = new DataBankerCourse();
	DataBankerEvent dbe = new DataBankerEvent();
	DataBankerMember dbm = new DataBankerMember();
	DataBankerUser dbu = new DataBankerUser();

	public String changeUser(User user) {
		return dbu.updateUser(user);
	}

	public void createBelt(String beltName) {
		dbc.createBelt(beltName);
	}

	public String createCourse(Course course) {
		return dbc.createCourse(course);
	}

	public Integer createEvent(Event event) {
		return dbe.createEvent(event);
	}

	public void deleteBeltByID(int beltID) {
		dbc.deleteBeltByID(beltID);
	}

	public void deleteCourseByID(int courseID) {
		dbc.deleteCourse(courseID);
	}

	public void deleteEventByEventID(int eventID) {
		dbe.deleteEvent(eventID);
		dbe.deleteParticipantsForEvent(eventID);
	}

	public void deleteMemberByMemberID(int memberID) {
		dbm.deleteMember(memberID);
	}

	public void deleteUserByUserID(int userID) {
		dbu.deleteUser(userID);
	}

	public ArrayList<Belt> getAvailableBelts() {
		return dbc.getAvailableBelts();
	}

	public Belt getBeltByID(int beltID) {
		return dbc.getBeltByID(beltID);
	}

	public ArrayList<String> getBeltList(String courseName) {
		int courseID = dbc.getCourseID(courseName);
		return dbc.getBelts(courseID);
	}

	public ArrayList<Course> getCompleteCourseList() {
		return dbc.getCourses();
	}

	public String getCourseBeltPair(int courseID, int beltID) {
		return dbc.getCourseBeltPair(courseID, beltID);
	}

	public Course getCourseByID(int courseID) {
		return dbc.getCourseByID(courseID);
	}

	public int getCourseID(String courseName) {
		return dbc.getCourseID(courseName);
	}

	public ArrayList<Integer> getCourseIDs(ArrayList<String> courseNames) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();

		Iterator itr = courseNames.iterator();
		while (itr.hasNext()) {
			numbers.add(dbc.getCourseID((String) itr.next()));

		}
		return numbers;

	}

	public ArrayList<String> getCourseList() {
		return dbc.getCourseNames();
	}

	public String getCourseName(int id) {
		return dbc.getCourseName(id);
	}

	public Event getEventByEventID(int eventID) {
		return dbe.getEvent(eventID);
	}

	public ArrayList<Event> getEventList() {
		return dbe.getEventList();
	}

	public ArrayList<EventParticipant> getEventParticipants(int eventID) {
		return dbe.getEventParticipants(eventID, dbm.getMemberList());
	}

	public ArrayList<User> getInstructorList() {

		return dbu.getUserList();
	}

	public Member getMemberByBarcodeID(int barcodeID) {
		return dbm.getMember(barcodeID);
	}

	public Member getMemberByMemberID(int memberID) {
		return dbm.getMemberWithMemberID(memberID);
	}

	public ArrayList<Member> getMemberList() {
		ArrayList<Member> memberList = dbm.getMemberList();
		return memberList;
	}

	public User getUserByUserID(int userID) {
		return dbu.getUser(userID);
	}

	public ArrayList<User> getUserList() {
		return dbu.getUserList();
	}

	public void renameBeltByID(Belt belt) {
		dbc.renameBeltByID(belt);
	}

	public String saveMember(Member member) {
		System.out.println("Member Courses: " + member.getCourses());
		System.out.println("Member Grades: " + member.getGraduations());
		System.out.println("Member Tariffs: " + member.getTariffs());
		return dbm.createMember(member);

	}

	public String saveUser(User user) {
		return dbu.createUser(user);
	}

	public ArrayList<Member> searchMember(String searchQuery) {
		return dbm.search(searchQuery);
	}

	public void setEventParticipants(int eventID,
			ArrayList<EventParticipant> participants) {
		dbe.setParticipantsForEvent(eventID, participants);
	}

	public void updateCourse(Course course) {
		dbc.updateCourse(course);
	}

	public Integer updateEvent(Event event) {
		return dbe.updateEvent(event);
	}

	public ArrayList<CourseTariff> getTariff(String courseName) {
		return dbc.getCourseTariffsForCourse(courseName);

	}

	public String updateMember(Member member) {
		return dbm.updateMember(member);
	}

	public ArrayList<String> getMemberCourses(int course, float tariff) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBankAccounts() {
		String fileName = "dtaus/dtaus0.txt";

		ArrayList<BankAccount> accounts = dbm.getBankAccounts();
		// initialisierung
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);

			DtausDateiWriter dtausDateiWriter = new DtausDateiWriter(fos);

			// Jetzt wird der ASatz gefüllt und geschrieben:
			dtausDateiWriter.setAGutschriftLastschrift("LK");
			dtausDateiWriter.setABLZBank(53250000);
			dtausDateiWriter.setAKundenname("Michael Moeller");
			dtausDateiWriter.setAKonto(3002201);
		//	Date aDat = new Date();
		//	aDat.setDate(15);
		//	aDat.setMonth(1);
		//	dtausDateiWriter.setAAusfuehrungsdatum(aDat);
			dtausDateiWriter.writeASatz();

			// Ab hier werden die eigentlichen Zahlungssätze erstellt:

			Iterator itr = accounts.iterator();

			while (itr.hasNext()) {
				BankAccount test = (BankAccount) itr.next();
				dtausDateiWriter.setCBLZEndbeguenstigt(Integer.parseInt(test
						.getBankNumber()));
				dtausDateiWriter.setCKonto(Integer.parseInt(test
						.getAccountNumber()));
				dtausDateiWriter
						.setCTextschluessel(CSatz.TS_LASTSCHRIFT_EINZUGSERMAECHTIGUNGSVERFAHREN);
				dtausDateiWriter.setCInterneKundennummer(test.getBarcodeId());
				dtausDateiWriter.setCBetragInEuro(dbm.getContribution(test
						.getBarcodeId()));

				String name = test.getForename() + " " + test.getSurname();
				name = name.replace("ä", "ae");
				name = name.replace("ö", "oe");
				name = name.replace("ü", "ue");
				name = name.replace("Ä", "Ae");
				name = name.replace("Ö", "Oe");
				name = name.replace("Ü", "Ue");

				dtausDateiWriter.setCName(name);
				dtausDateiWriter.addCVerwendungszweck("Monatlicher Beitrag");
				dtausDateiWriter.writeCSatz();
			}
			// E-Satz schreiben = Ende einer logischen Datei.<br>
			dtausDateiWriter.writeESatz();

			dtausDateiWriter.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileName;
	}
}
