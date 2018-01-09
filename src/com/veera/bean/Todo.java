package com.veera.bean;

public class Todo {

	private long oid;
	private String description;
	private int numberOfDays;
	private boolean completed;
	private boolean active;
	
	private long loginOid;

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getLoginOid() {
		return loginOid;
	}

	public void setLoginOid(long loginOid) {
		this.loginOid = loginOid;
	}
	
	

}
