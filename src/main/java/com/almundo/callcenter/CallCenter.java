package com.almundo.callcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.almundo.callcenter.models.*;
import com.almundo.callcenter.service.Dispatcher;
import com.almundo.callcenter.service.ManageCall;

/**
 * 
 * @author Nerly Aguilera
 *
 */
@SpringBootApplication
public class CallCenter implements CommandLineRunner {

	@Autowired
	Dispatcher dispatcher;

	public static void main(String[] args) {
		SpringApplication.run(CallCenter.class, args);
	}

	public void run(String... args) throws Exception {

		PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<Employee>();
		employees.add(new Operator(1));
		employees.add(new Operator(2));
		employees.add(new Supervisor(1));
		employees.add(new Director(1));

		// employees queue
		dispatcher.dispatchEmployees(employees);

		// Quantity of Threads
		ExecutorService threadPool = Executors.newFixedThreadPool(10);

		List<Callable<Call>> callablesList = new ArrayList<Callable<Call>>();

		// Quantity of incoming calls
		for (int i = 1; i <= 10; i++) {
			callablesList.add(new ManageCall(dispatcher, new Call(i, StatusCall.INCOMING)));
		}

		threadPool.invokeAll(callablesList);
		threadPool.shutdown();
	}

}
