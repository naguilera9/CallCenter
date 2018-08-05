package com.almundo.callcenter.models;

public class Operator extends Employee {

	public Operator(int id) {
		super(id);
		setCharge(Charge.OPERATOR);
	}

}
