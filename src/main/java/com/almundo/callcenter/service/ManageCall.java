package com.almundo.callcenter.service;

import java.util.concurrent.Callable;

import com.almundo.callcenter.models.Call;

/**
 * Class to recibe the call and the dispatcher to make or connect the call
 * 
 * @author Nerly-PC
 *
 */
public class ManageCall implements Callable<Call> {

	private Dispatcher dispatcher;
	private Call call;

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public ManageCall(Dispatcher dispatcher, Call call) {
		super();
		this.dispatcher = dispatcher;
		this.call = call;
	}

	/**
	 * Method that make the threads for each call
	 */
	public Call call() throws Exception {
		return dispatcher.dispatchCall(call);
	}

}
