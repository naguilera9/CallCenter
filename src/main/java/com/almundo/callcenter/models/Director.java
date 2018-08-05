package com.almundo.callcenter.models;

public class Director extends Employee {

	public Director(int id) {
		super(id);
		setCharge(Charge.DIRECTOR);
	}

}
