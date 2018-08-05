package com.almundo.callcenter.models;

import com.almundo.callcenter.models.Employee;

public class Employee implements Comparable<Employee> {

	private int id;
	private Charge charge;

	public Employee(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}

	public int compareTo(Employee emplo) {
		return this.charge.compareTo(emplo.charge);
	}

}
