package com.almundo.callcenter.service;

import java.util.concurrent.PriorityBlockingQueue;

import com.almundo.callcenter.models.Call;
import com.almundo.callcenter.models.Employee;

public interface Dispatcher {

	/**
	 * Receive employees list
	 * 
	 * @param employees
	 */
	public void dispatchEmployees(PriorityBlockingQueue<Employee> employees);

	/**
	 * Delegate each call for the free employee
	 * 
	 * @param call
	 * @return Call
	 */
	public Call dispatchCall(Call call);

	/**
	 * Changed status to the call
	 * 
	 * @param call
	 * @param employee
	 * @return
	 */
	public Call makeCall(Call call, Employee employee);

	public void setProperties(MyProperties properties);
}
