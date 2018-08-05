package com.almundo.callcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import com.almundo.callcenter.models.*;
import com.almundo.callcenter.service.Dispatcher;
import com.almundo.callcenter.service.DispatcherImpl;
import com.almundo.callcenter.service.ManageCall;
import com.almundo.callcenter.service.MyProperties;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class CallCenterTest extends TestCase {

	Dispatcher dispatcher;

	MyProperties properties;

	PriorityBlockingQueue<Employee> employees;

	/**
	 * Init mocks before the tests
	 */
	@Before
	public void setUp() {
		properties = Mockito.mock(MyProperties.class);
		dispatcher = new DispatcherImpl();
		dispatcher.setProperties(properties);
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * Test case #1
	 * 
	 * Test with 2 operators,1 supervisor and 1 Director and 10 calls When all the
	 * employees are busy, the call is waiting while a free employee picks up the
	 * call
	 */
	@org.junit.Test
	public void testSameCallsThanThreads() {

		employees = new PriorityBlockingQueue<Employee>();
		employees.add(new Operator(1));
		employees.add(new Operator(2));
		employees.add(new Supervisor(1));
		employees.add(new Director(1));

		Mockito.when(properties.getMaxDuration()).thenReturn(10000);
		Mockito.when(properties.getMinimumDuration()).thenReturn(5000);

		dispatcher.dispatchEmployees(employees);

		try {

			ExecutorService quantityThread = Executors.newFixedThreadPool(10);
			List<Callable<Call>> listCalled = new ArrayList<Callable<Call>>();
			// Quantity of calls 10
			for (int i = 1; i <= 10; i++) {
				listCalled.add(new ManageCall(dispatcher, new Call(i, StatusCall.INCOMING)));
			}
			List<Future<Call>> futures = quantityThread.invokeAll(listCalled);

			int counter = 0;
			for (Future<Call> future : futures) {
				assertNotNull(future.get());
				counter++;
			}
			// Attend on all the calls
			assertEquals(counter, 10);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Test case #2
	 * 
	 * Test with 2 operators,1 supervisor and 1 Director and 15 calls When all the
	 * employees are busy the call is waiting while a free employee picks up the
	 * call and when the 10 threads are busy, the call start to wait to a thread is
	 * completed
	 */
	@org.junit.Test
	public void testMoreCallsThanThreads() {

		employees = new PriorityBlockingQueue<Employee>();
		employees.add(new Operator(1));
		employees.add(new Operator(2));
		employees.add(new Supervisor(1));
		employees.add(new Director(1));

		Mockito.when(properties.getMaxDuration()).thenReturn(10000);
		Mockito.when(properties.getMinimumDuration()).thenReturn(5000);

		dispatcher.dispatchEmployees(employees);

		try {

			ExecutorService quantityThread = Executors.newFixedThreadPool(10);
			List<Callable<Call>> listCalled = new ArrayList<Callable<Call>>();
			// Quantity of calls=15
			for (int i = 1; i <= 15; i++) {
				listCalled.add(new ManageCall(dispatcher, new Call(i, StatusCall.INCOMING)));
			}
			List<Future<Call>> futures = quantityThread.invokeAll(listCalled);

			int counter = 0;
			for (Future<Call> future : futures) {
				assertNotNull(future.get());
				counter++;
			}
			// Attend on all the calls
			assertEquals(counter, 15);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
