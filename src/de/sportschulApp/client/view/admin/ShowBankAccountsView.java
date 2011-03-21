package de.sportschulApp.client.view.admin;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.sportschulApp.client.presenter.admin.ShowBankAccountsPresenter;
import de.sportschulApp.client.view.localization.LocalizationConstants;

public class ShowBankAccountsView extends Composite implements
		ShowBankAccountsPresenter.Display {

	Button generateButton;
	private Anchor anchor;

	public ShowBankAccountsView(LocalizationConstants constants) {
		VerticalPanel bankAccountsWrapper = new VerticalPanel();
		initWidget(bankAccountsWrapper);
		bankAccountsWrapper.addStyleName("bankAccountWrapper");
		
		VerticalPanel header = new VerticalPanel();
		header.addStyleName("formWrapper");
		header.setWidth("100%");

		HorizontalPanel formHeader = new HorizontalPanel();
		formHeader.addStyleName("formHeader");
		formHeader.add(new Label("Bankdaten"));

		
		generateButton = new Button("Dtaus Datei generieren");
		Label textLabel = new Label(
				"Mit einem Klick auf \"Dtaus Datei generieren\" erzeugen Sie eine \"Dtaus\" datei. Diese können Sie mit einem Rechtsklick auf den erscheinenden Link \"Dtaus speichern\" mittels \"Ziel speichern unter\" auf Ihrem PC sichern.");

		textLabel.setWidth("500px");
		header.add(formHeader);
		header.add(textLabel);

		bankAccountsWrapper.add(header);
		bankAccountsWrapper.add(generateButton);
		
		anchor = new Anchor("Dtaus speichern");
		anchor.setHref("dtaus/dtaus0.txt");
		anchor.setVisible(false);
		
		bankAccountsWrapper.add(anchor);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public HasClickHandlers getButton() {
		return generateButton;
	}
	
	public Anchor getAnchor(){
		return anchor;
	}

}
