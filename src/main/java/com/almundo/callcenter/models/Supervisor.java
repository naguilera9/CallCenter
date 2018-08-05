package com.almundo.callcenter.models;

public class Supervisor extends Employee {

	public Supervisor(int id) {
		super(id);
		setCharge(Charge.SUPERVISOR);
	}

}
