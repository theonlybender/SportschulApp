package de.sportschulApp.client.presenter.admin;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.rpc.client.RpcService;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import de.sportschulApp.shared.BankAccount;
import de.sportschulApp.client.presenter.Presenter;
import de.sportschulApp.client.services.AdminServiceAsync;
import de.sportschulApp.client.view.admin.ShowBankAccountsView;

public class ShowBankAccountsPresenter implements Presenter

{
	public interface Display {
		Widget asWidget();
		HasClickHandlers getButton();
		
	}
	private final Display display;
	private final HandlerManager eventBus;
	private final AdminServiceAsync rpcService;

	public ShowBankAccountsPresenter(AdminServiceAsync rpcService,HandlerManager eventBus, Display display) {
		this.eventBus = eventBus;
		this.display = display;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		display.getButton().addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				rpcService.getBankAccounts(new AsyncCallback<ArrayList<BankAccount>>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					public void onSuccess(ArrayList<BankAccount> result) {
						System.out.println("RESULT forename: "+result.get(1).getForename());
						System.out.println("RESULT surname: "+result.get(1).getSurname());
						System.out.println("RESULT bankname: "+result.get(1).getBankName());

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
