package de.sportschulApp.client.presenter.admin;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.sportschulApp.client.presenter.Presenter;
import de.sportschulApp.client.services.AdminServiceAsync;


public class ShowBankAccountsPresenter implements Presenter

{
	public interface Display {
		Widget asWidget();

		HasClickHandlers getButton();

	}

	private final Display display;
	private final HandlerManager eventBus;
	private final AdminServiceAsync rpcService;

	public ShowBankAccountsPresenter(AdminServiceAsync rpcService,
			HandlerManager eventBus, Display display) {
		this.eventBus = eventBus;
		this.display = display;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		display.getButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				rpcService.getBankAccounts(new AsyncCallback<String>() {

					public void onFailure(Throwable caught) {

					}

					public void onSuccess(String result) {
						System.out.println("filename: " + result);
					}

				});
			}
		});
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
