package com.almundo.callcenter.models;

public class Call {
	private int id;
	private StatusCall status;
	private Integer time;

	public Call(int id, StatusCall status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StatusCall getStatus() {
		return status;
	}

	public void setStatus(StatusCall status) {
		this.status = status;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
