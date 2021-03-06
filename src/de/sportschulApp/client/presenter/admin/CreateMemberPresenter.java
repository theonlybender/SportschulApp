package de.sportschulApp.client.presenter.admin;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnStatusChangedHandler;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.SingleUploader;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

import de.sportschulApp.client.presenter.Presenter;
import de.sportschulApp.client.services.AdminServiceAsync;
import de.sportschulApp.client.view.admin.CreateMemberView.CourseSelectorWidget;
import de.sportschulApp.client.view.localization.LocalizationConstants;
import de.sportschulApp.shared.CourseTariff;
import de.sportschulApp.shared.Member;
import eu.maydu.gwt.validation.client.ValidationProcessor;
import eu.maydu.gwt.validation.client.actions.StyleAction;
import eu.maydu.gwt.validation.client.description.PopupDescription;
import eu.maydu.gwt.validation.client.i18n.ValidationMessages;
import eu.maydu.gwt.validation.client.validators.numeric.IntegerValidator;
import eu.maydu.gwt.validation.client.validators.standard.NotEmptyValidator;
import eu.maydu.gwt.validation.client.validators.strings.StringLengthValidator;

public class CreateMemberPresenter implements Presenter {
	public interface Display {
		void addNewCourseSelector(String courseName, float tariff, int graduation);

		Widget asWidget();

		void fillForm(Member result);

		TextBoxBase getAccountForenameTextBox();

		TextBoxBase getAccountNumberTextBox();

		TextBoxBase getAccountSurnameTextBox();

		TextBox getAccoutForenameTextbox();

		TextBox getAccoutSurnameTextbox();

		TextBoxBase getBankNameTextBox();

		TextBoxBase getBankNumberTextBox();

		TextBox getBarcodeTextBox();

		ListBox getBeltsizeTextBox();

		ListBox getBirthTextBox1();

		ListBox getBirthTextBox2();

		ListBox getBirthTextBox3();

		TextBox getCityTextBox();

		LocalizationConstants getConstants();

		HasChangeHandlers getCourseHandler(int index);

		ArrayList<CourseSelectorWidget> getCourseList();

		ListBox getCourseListBox();

		TextArea getDiseasesTextBox();

		TextBox getEmailTextBox();

		TextBox getFaxTextBox();

		CheckBox getForenameCheckBox();

		TextBox getForenameTextBox();

		HasChangeHandlers getGradeHandler(int index);

		ListBox getGradeListBox();

		TextBox getHomepageTextBox();

		String getListBoxString();

		TextBox getmobilephoneTextBox();

		TextArea getNoteTextBox();

		TextBox getPhoneTextBox();

		String getPictureUrl();

		int getSelectedBeltNr(int index);

		float getSelectedTariff(int index);

		String getSelectedCourseName(int index);

		HasClickHandlers getSendButton();

		HasClickHandlers getNewCourseSelectorLabel();

		TextBox getStreetTextBox();

		CheckBox getSurnameCheckBox();

		TextBox getSurnameTextBox();

		MultiUploader getUploadHandler();

		ValidationProcessor getValidator();

		TextBox getZipcodeTextBox();

		void setBeltList(int index, ArrayList<String> beltList);

		void setCourseList(ArrayList<String> courseList);

		void setImage(PreloadedImage image, String imageUrl);

		void setTariffList(int test, ArrayList<CourseTariff> result);

		int calculateTrainingUnits();

		void addNewCourseSelector();

		void removeLastCourseSelector();

		void setImageUrl(String serverResponse);

	}

	private boolean								createView;
	private LocalizationConstants				constants;
	private final Display						display;
	private boolean								error					= false;
	private ArrayList<Integer>					grades;
	private String								imageUrl;
	// Load the image in the document and in the case of success attach it to
	// the viewer
	private IUploader.OnFinishUploaderHandler	onFinishUploaderHandler	= new IUploader.OnFinishUploaderHandler() {

																			public void onFinish(IUploader uploader) {
																				if (uploader.getStatus() == Status.SUCCESS) {
																					// Window.alert("server response: "+uploader.getServerResponse());
																					imageUrl = uploader.getServerResponse();
																					int start = imageUrl.indexOf("[CDATA[");
																					start = start + 7;
																					int ende = imageUrl.indexOf("]]");
																					// Window.alert("START: "+start+"       ENDE: "+ende);
																					// Window.alert("SUBSTRING: "+imageUrl.substring(start,ende));

																					// display.setImageUrl(uploader.getServerResponse());
																					imageUrl = "uploads/member/" + imageUrl.substring(start, ende);
																					new PreloadedImage(imageUrl, showImage);
																					// TODO
																				}

																			}

																		};
	private PopupDescription					popupDesc;

	private final AdminServiceAsync				rpcService;

	// Attach an image to the pictures viewer
	private OnLoadPreloadedImageHandler			showImage				= new OnLoadPreloadedImageHandler() {
																			public void onLoad(PreloadedImage image) {
																				image.setWidth("100px");

																				display.setImage(image, imageUrl);

																				// TODO
																			}
																		};

	private ValidationProcessor					validator;
	private ArrayList<Float>					tariffs;
	private float								tariff;
	private int									graduation;
	private int									courseSelectorIndex;

	public CreateMemberPresenter(AdminServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.display = display;
		this.rpcService = rpcService;
		constants = display.getConstants();
		bind();
		getCourseList();
		setupValidation();

	}

	/**
	 * Konstruktor für den EditView
	 * 
	 * @param rpcService
	 * @param eventBus
	 * @param display
	 * @param barcodeID
	 *            (Aus HistroyToken von ShowMember)
	 */
	public CreateMemberPresenter(AdminServiceAsync rpcService, HandlerManager eventBus, Display display, String barcodeID) {
		this.display = display;
		this.rpcService = rpcService;
		constants = display.getConstants();
		getMember(barcodeID);
		bind();
		getCourseList();
		setupValidation();
		createView = true;

	}

	private void bind() {
		display.getSendButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				boolean success = display.getValidator().validate();
				if (display.getBirthTextBox1().getSelectedIndex() == 0) {
					display.getBirthTextBox1().setStyleName("validationFailedBorder");
					success = false;
				} else {
					display.getBirthTextBox1().removeStyleName("validationFailedBorder");
				}
				if (display.getBirthTextBox2().getSelectedIndex() == 0) {
					display.getBirthTextBox2().setStyleName("validationFailedBorder");
					success = false;
				} else {
					display.getBirthTextBox2().removeStyleName("validationFailedBorder");
				}
				if (display.getBirthTextBox3().getSelectedIndex() == 0) {
					display.getBirthTextBox3().setStyleName("validationFailedBorder");
					success = false;
				} else {
					display.getBirthTextBox3().removeStyleName("validationFailedBorder");
				}
				if (display.getBeltsizeTextBox().getSelectedIndex() == 0) {
					display.getBeltsizeTextBox().setStyleName("validationFailedBorder");
					success = false;
				} else {
					display.getBeltsizeTextBox().removeStyleName("validationFailedBorder");
				}
				if (success) {
					System.out.println("validation success");
					fillForm();

				} else {
					System.out.println("validation error");
					Window.alert("Bitte überprüfen Sie ihre Eingaben");

					// One (or more) validations failed. The actions will have
					// been
					// already invoked by the validator.validate() call.
				}
			}
		});

		display.getUploadHandler().addOnFinishUploadHandler(onFinishUploaderHandler);

		for (int i = 0; i < 10; i++) {
			final int test = i;
			display.getCourseHandler(i).addChangeHandler(new ChangeHandler() {

				public void onChange(ChangeEvent event) {
					getBeltList(test);
					getTariffList(test);
				}
			});
			display.getGradeHandler(i).addChangeHandler(new ChangeHandler() {

				public void onChange(ChangeEvent event) {
					display.getSelectedTariff(test);
				}
			});
		}

		display.getNewCourseSelectorLabel().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.addNewCourseSelector();
			}
		});

		display.getForenameCheckBox().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (display.getForenameCheckBox().getValue()) {
					// haken setzen
					display.getAccoutForenameTextbox().setText(constants.likeAbove());
					display.getAccoutForenameTextbox().setReadOnly(true);
				} else {
					// haken entfernen
					display.getAccoutForenameTextbox().setText("");
					display.getAccoutForenameTextbox().setReadOnly(false);
					display.getAccoutForenameTextbox().setFocus(true);
				}
			}
		});

		display.getSurnameCheckBox().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (display.getSurnameCheckBox().getValue()) {
					// haken setzen
					display.getAccoutSurnameTextbox().setText(constants.likeAbove());
					display.getAccoutSurnameTextbox().setReadOnly(true);
				} else {
					// haken entfernen
					display.getAccoutSurnameTextbox().setText("");
					display.getAccoutSurnameTextbox().setReadOnly(false);
					display.getAccoutSurnameTextbox().setFocus(true);
				}
			}
		});
	}

	public void fillForm() {
		error = false;
		// courses = new ArrayList<Integer>();
		grades = new ArrayList<Integer>();
		tariffs = new ArrayList<Float>();
		ArrayList<CourseSelectorWidget> courseListWidget = display.getCourseList();

		ArrayList<String> courseNames = new ArrayList<String>();

		for (int index = 0; index < 15; index++) {
			if (!courseListWidget.get(index).getSelectedCourseName().equals(display.getListBoxString())) {
				if ((!courseListWidget.get(index).getSelectedBeltName().equals(display.getListBoxString()) && (!courseListWidget.get(index).getSelectedTariffName().equals(display.getListBoxString())))) {
					courseNames.add(courseListWidget.get(index).getSelectedCourseName());
					grades.add(display.getSelectedBeltNr(index));
					tariffs.add(display.getSelectedTariff(index));

				} else {
					error = true;
					Window.alert("Sie haben einen Kurs ohne Gürtelfarbe oder Tarif angegeben");
				}
			}
		}
		if (error == false) {

			System.out.println("courseNames: " + courseNames);

			rpcService.getCourseIDs(courseNames, new AsyncCallback<ArrayList<Integer>>() {

				private String	accountForename;
				private String	accountSurname;

				public void onFailure(Throwable caught) {
					System.out.println("rpc errror");
				}

				public void onSuccess(ArrayList<Integer> result) {

					// prüfen ob "wie oben" bei Vorname gesetzt
					if (display.getForenameCheckBox().getValue()) {
						accountForename = display.getForenameTextBox().getText();
					} else {
						accountForename = display.getAccountForenameTextBox().getText();
					}

					// prüfen ob "wie oben" bei Nachname gesetzt
					if (display.getSurnameCheckBox().getValue()) {
						accountSurname = display.getSurnameTextBox().getText();
					} else {
						accountSurname = display.getAccountSurnameTextBox().getText();
					}

					// courses = result;
					Integer selected = display.getBirthTextBox1().getSelectedIndex();
					String birthDay = selected.toString();

					selected = display.getBirthTextBox2().getSelectedIndex();
					String birthMonth = selected.toString();

					selected = display.getBirthTextBox3().getSelectedIndex();
					String birthYear = display.getBirthTextBox3().getItemText(selected);
					Member member = new Member(0, new Integer(display.getBarcodeTextBox().getText()), display.getForenameTextBox().getText(), display.getSurnameTextBox().getText(), new Integer(display.getZipcodeTextBox().getText()), display.getCityTextBox().getText(), display.getStreetTextBox()
							.getText(), display.getPhoneTextBox().getText(), display.getmobilephoneTextBox().getText(), display.getFaxTextBox().getText(), display.getEmailTextBox().getText(), display.getHomepageTextBox().getText(), birthDay, birthMonth, birthYear, display.getPictureUrl(), display
							.getDiseasesTextBox().getText(), getBeltsize(), display.getNoteTextBox().getText(), display.calculateTrainingUnits(), accountForename, accountSurname, display.getAccountNumberTextBox().getText(), display.getBankNameTextBox().getText(), display.getBankNumberTextBox()
							.getText(), result, grades, tariffs);

					// TODO
					try {
						if (member.getPicture() == null) {
							member.setPicture("imgs/standartMember.jpg");
						}
					} catch (Exception e) {
					}
					if (createView) {

						rpcService.updateMember(member, new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								System.out.println("rpc errror");
							}

							public void onSuccess(String result) {
								Window.alert("Mitgliedseintrag erfolgreich geändert.");
								History.newItem("adminMembersShowMembers");

							}

						});

					} else {
						rpcService.saveMember(member, new AsyncCallback<String>() {

							public void onFailure(Throwable caught) {
								System.out.println("rpc errror");
							}

							public void onSuccess(String result) {
								System.out.println("result: " + result);
								if (result.equals("barcode_id already used")) {
									display.getBarcodeTextBox().setStyleName("validationFailedBorderBarcode");
									Window.alert(display.getConstants().barcodeUsed());
								} else {
									Window.alert(constants.memberCreated());
									History.newItem("adminMembersShowMembers");

								}
							}
						});
					}

				}

			});
		}
	}

	private String getBeltsize() {
		return display.getBeltsizeTextBox().getItemText(display.getBeltsizeTextBox().getSelectedIndex());
	}

	public void getTariffList(int index) {
		final int test = index;
		rpcService.getTariff(display.getSelectedCourseName(index), new AsyncCallback<ArrayList<CourseTariff>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(ArrayList<CourseTariff> result) {
				display.setTariffList(test, result);

			}

		});
	}

	public void getTariffListForEdit(final int index, final float tariff) {
		final int test = index;
		rpcService.getTariff(display.getSelectedCourseName(index), new AsyncCallback<ArrayList<CourseTariff>>() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			public void onSuccess(ArrayList<CourseTariff> result) {
				display.setTariffList(test, result);
				for (int i = 1; i < display.getCourseList().get(index).getTariffListBox().getItemCount(); i++) {
					String tempDatabase = display.getCourseList().get(index).getTariffListBox().getItemText(i);
					int temp2 = tempDatabase.indexOf("-");
					tempDatabase = tempDatabase.substring(temp2 + 2, tempDatabase.length() - 2);

					String tempTariff = Float.toString(tariff);
					if (tempTariff.contains(".")) {
						tempTariff = tempTariff.substring(0, tempTariff.indexOf("."));
					}
					if (tempDatabase.contains(".")) {
						tempDatabase = tempDatabase.substring(0, tempDatabase.indexOf("."));

					}
					if (Float.parseFloat(tempDatabase) == Float.parseFloat(tempTariff)) {

						display.getCourseList().get(index).getTariffListBox().setSelectedIndex(i);

					} else {
						
					}
				}

			}

		});
	}

	public void getBeltList(int index) {
		final int test = index;
		rpcService.getBeltList(display.getSelectedCourseName(index), new AsyncCallback<ArrayList<String>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(ArrayList<String> result) {
				display.setBeltList(test, result);
			}
		});

	}

	public void getCourseList() {
		rpcService.getCourseList(new AsyncCallback<ArrayList<String>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(ArrayList<String> result) {
				display.setCourseList(result);
			}
		});

	}

	public void getBeltListForEdit(final int index, final int graduation) {
		final int test = index;
		rpcService.getBeltList(display.getSelectedCourseName(index), new AsyncCallback<ArrayList<String>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(ArrayList<String> result) {
				display.setBeltList(test, result);

				System.out.println("GRADUATION:" + graduation + ":test");

				for (int i = 0; i < result.size(); i++) {
					System.out.println(display.getCourseList().get(index).getGraduationListBox().getItemText(i));
				}
				display.getCourseList().get(index).getGraduationListBox().setSelectedIndex(graduation);

			}
		});
	}

	private void getMember(String barcodeID) {
		rpcService.getMemberByBarcodeID(Integer.parseInt(barcodeID), new AsyncCallback<Member>() {

			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Member result) {
				display.fillForm(result);

				int course, graduation;
				float tariff;
				for (int i = 0; i < result.getCourses().size(); i++) {
					course = result.getCourses().get(i);
					tariff = result.getTariffs().get(i);
					graduation = result.getGraduations().get(i);
					getMemberCourses(course, tariff, graduation, i);
				}
				display.removeLastCourseSelector();
			}
		});
	}

	public void getMemberCourses(int course, final float tariff, final int graduation, final int index1) {

		display.addNewCourseSelector();

		rpcService.getCourseName(course, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(String result) {

				fillCourseSelector(result, tariff, graduation, index1);
				getTariffListForEdit(index1, tariff);
				getBeltListForEdit(index1, graduation);

			}

		});

	}

	private void fillCourseSelector(String result, float tariff, int graduation, int index) {
		for (int i = 0; i < display.getCourseList().get(index).getCourseListBox().getItemCount(); i++) {

			if (display.getCourseList().get(index).getCourseListBox().getItemText(i).equals(result)) {
				display.getCourseList().get(index).getCourseListBox().setItemSelected(i, true);
			}
		}

	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	private void setupValidation() {
		class CustomValidationMessages extends ValidationMessages {

			@Override
			public String getDescriptionMessage(String msgKey) {
				HashMap<String, String> msgMap = new HashMap<String, String>();
				msgMap.put("forename", constants.popupHelpForename());
				msgMap.put("surname", constants.popupHelpSurname());
				msgMap.put("barcode", constants.popupHelpBarcode());
				msgMap.put("street", constants.popupHelpStreet());
				msgMap.put("zipcode", constants.popupHelpZipcode());
				msgMap.put("city", constants.popupHelpCity());
				msgMap.put("phone", constants.popupHelpPhone());
				msgMap.put("beltsize", constants.popupHelpBeltsize());
				msgMap.put("mobilephone", constants.popupHelpMobilephone());
				msgMap.put("fax", constants.popupHelpFax());
				msgMap.put("email", constants.popupHelpEmail());
				msgMap.put("homepage", constants.popupHelpHomepage());
				msgMap.put("diseases", constants.popupHelpDiseases());
				msgMap.put("note", constants.popupHelpNote());
				msgMap.put("accountForename", constants.popupHelpAccountForename());
				msgMap.put("accountSurname", constants.popupHelpAccountSurename());
				msgMap.put("accountBankname", constants.popupHelpBankname());
				msgMap.put("accountBanknumber", constants.popupHelpBanknumber());
				msgMap.put("accountAccountNumber", constants.popupHelpAccountNumber());

				String temp = msgMap.get(msgKey.trim());
				return temp;
			}
		}

		validator = display.getValidator();
		ValidationMessages messages = new CustomValidationMessages();

		popupDesc = new PopupDescription(messages);

		validator.addValidators("forename", new StringLengthValidator(display.getForenameTextBox(), 2, 30).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("surname", new StringLengthValidator(display.getSurnameTextBox(), 2, 30).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("barcode", new IntegerValidator(display.getBarcodeTextBox(), 0, Integer.MAX_VALUE).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("street", new StringLengthValidator(display.getStreetTextBox(), 2, 30).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("zipcodeInt", new IntegerValidator(display.getZipcodeTextBox(), 0, Integer.MAX_VALUE).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("zipcodeLength", new StringLengthValidator(display.getZipcodeTextBox(), 5, 5).addActionForFailure(new StyleAction("validationFailedBorder"))
		// .addActionForFailure(new LabelTextAction(forenameErrorLabel))
				);

		validator.addValidators("city", new StringLengthValidator(display.getCityTextBox(), 2, 30).addActionForFailure(new StyleAction("validationFailedBorder")));

		/*
		 * validator.addValidators("beltsize", new
		 * NotEmptyValidator(display.getBeltsizeTextBox())
		 * .addActionForFailure(new StyleAction( "validationFailedBorder")));
		 */
		validator.addValidators("accountForename", new StringLengthValidator(display.getAccountForenameTextBox(), 2, 30).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("accountSurname", new StringLengthValidator(display.getAccountSurnameTextBox(), 2, 30).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("Bankname", new StringLengthValidator(display.getBankNameTextBox(), 2, 30).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("accountNumber", new IntegerValidator(display.getAccountNumberTextBox(), 0, Integer.MAX_VALUE).addActionForFailure(new StyleAction("validationFailedBorder")));

		validator.addValidators("Banknumber", new StringLengthValidator(display.getBankNumberTextBox(), 1, 8).addActionForFailure(new StyleAction("validationFailedBorder")));

		popupDesc.addDescription("forename ", display.getForenameTextBox());
		popupDesc.addDescription("surname ", display.getSurnameTextBox());
		popupDesc.addDescription("barcode ", display.getBarcodeTextBox());
		popupDesc.addDescription("street ", display.getStreetTextBox());
		popupDesc.addDescription("zipcode ", display.getZipcodeTextBox());
		popupDesc.addDescription("city ", display.getCityTextBox());
		popupDesc.addDescription("phone ", display.getPhoneTextBox());
		// popupDesc.addDescription("beltsize ", display.getBeltsizeTextBox());
		popupDesc.addDescription("mobilephone ", display.getmobilephoneTextBox());
		popupDesc.addDescription("fax ", display.getFaxTextBox());
		popupDesc.addDescription("email ", display.getEmailTextBox());
		popupDesc.addDescription("homepage ", display.getDiseasesTextBox());
		popupDesc.addDescription("note ", display.getNoteTextBox());
		popupDesc.addDescription("accountForename ", display.getAccountForenameTextBox());
		popupDesc.addDescription("accountSurname ", display.getAccountSurnameTextBox());
		popupDesc.addDescription("accountAccountNumber ", display.getAccountNumberTextBox());
		popupDesc.addDescription("accountBanknumber ", display.getBankNumberTextBox());
		popupDesc.addDescription("accountBankname ", display.getBankNameTextBox());

	}
}
