package de.sportschulApp.client.presenter.trainer;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.sportschulApp.client.presenter.Presenter;
import de.sportschulApp.client.services.TrainerServiceAsync;
import de.sportschulApp.shared.Event;

public class NewEventPresenter implements Presenter {

	public interface Display {
		Widget asWidget();
		void setEventListBox(ArrayList<Event> events);
		HasClickHandlers getContinueButton();
	}

	private final Display display;
	private final TrainerServiceAsync rpcService;
	Date today = new Date();

	public NewEventPresenter(TrainerServiceAsync rpcService,
			HandlerManager eventBus, Display display) {
		this.display = display;
		this.rpcService = rpcService;
		bind();
		fillEventListBox();
	}

	private void bind() {
		
	}
	
	public void fillEventListBox() {
		rpcService.getEventList(new AsyncCallback<ArrayList<Event>>() {
			public void onSuccess(ArrayList<Event> result) {
				display.setEventListBox(result);
			}
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim laden der Events");
			}
		});
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}