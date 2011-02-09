package de.sportschulApp.client.view.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.sportschulApp.client.presenter.admin.ShowBankAccountsPresenter;
import de.sportschulApp.client.view.localization.LocalizationConstants;

public class ShowBankAccountsView extends Composite implements
		ShowBankAccountsPresenter.Display {

	Button testButton;

	public ShowBankAccountsView(LocalizationConstants constants) {
		VerticalPanel dummyPanel = new VerticalPanel();
		initWidget(dummyPanel);

		testButton=new Button("Bankdaten");
		Label dummyLabel = new Label("Da!");

	
		dummyPanel.add(dummyLabel);
		dummyPanel.add(testButton);

	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public HasClickHandlers getButton() {
		return testButton;
	}

}
