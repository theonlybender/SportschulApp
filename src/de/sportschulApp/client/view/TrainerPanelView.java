package de.sportschulApp.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.sportschulApp.client.presenter.TrainerPanelPresenter;

public class TrainerPanelView extends Composite implements TrainerPanelPresenter.Display {

	VerticalPanel contentContainer;
	VerticalPanel navigationContainer;
	VerticalPanel trainerPanelContainer;

	public TrainerPanelView() {

		trainerPanelContainer = new VerticalPanel();
		trainerPanelContainer.setSize("100%", "100%");

		navigationContainer = new VerticalPanel();
		navigationContainer.setWidth("100%");

		contentContainer = new VerticalPanel();
		contentContainer.setSize("100%", "100%");

		initWidget(trainerPanelContainer);

		trainerPanelContainer.add(navigationContainer);
		trainerPanelContainer.add(contentContainer);

	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public HasWidgets getContentContainer() {
		return contentContainer;
	}

	public HasWidgets getNavigationContainer() {
		return navigationContainer;
	}
	
	public VerticalPanel getContainer(){
		return navigationContainer;
	}
}
