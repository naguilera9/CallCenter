package com.almundo.callcenter.service;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.almundo.callcenter.models.Employee;
import com.almundo.callcenter.models.StatusCall;
import com.almundo.callcenter.models.Call;

@Service
public class DispatcherImpl implements Dispatcher{
	
	private PriorityBlockingQueue<Employee> employees;
	
    @Autowired
    MyProperties properties;
    
    /**
	 * Delegate each call for the free employee
	 * 
	 * @param call
	 * @return Call
	 */
	public Call dispatchCall(Call call) {
		try {	
				//There aren't free employees
				if(employees.isEmpty()) {
					System.out.println("Call "+call.getId()+" waiting ");
				} 
				//Send the call to the free employee (employees.take())
				makeCall(call,employees.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return call;
	}

	/**
	 * Change status to the call and set the duration time
	 * 
	 * @param call
	 * @param employee
	 * @return call
	 */
	public Call makeCall(Call call,Employee employee){
	
		call.setStatus(StatusCall.CONNECTED);
		System.out.println("Call "+call.getId()+ " Status "+call.getStatus()+" attended by "+employee.getCharge()+" "+employee.getId());
		
		try {
            Integer callTime = new Random().nextInt((properties.getMaxDuration() - properties.getMinimumDuration()) + 1) + properties.getMinimumDuration();
            
            Thread.sleep(callTime);
            //Next to the duration of the call change status of the call
            call.setStatus(StatusCall.FINISHED);
            //Put the duration call
            call.setTime(callTime);
            //Put the employee in the list of available
            employees.add(employee);
            
            System.out.println("Call "+call.getId()+ " Status "+call.getStatus()+" attended by "+employee.getCharge()+" "+employee.getId()+ " in "+callTime);
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
		
		return call;
	}

	/**
	 * Receive employees list
	 * @param employees
	 */
	public void dispatchEmployees(PriorityBlockingQueue<Employee> employees) {
		this.employees = employees;		
	}

	public MyProperties getProperties() {
		return properties;
	}

	public void setProperties(MyProperties properties) {
		this.properties = properties;
	}
	
}
