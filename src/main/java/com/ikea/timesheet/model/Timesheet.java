package com.ikea.timesheet.model;

import java.sql.Timestamp;

public class Timesheet {

	private long id;

	private Timestamp loginTime;

	private Timestamp logoutTime;

	private String currentDate;

	public Timesheet(int id, String currentDate, Timestamp loginTime, Timestamp logoutTime) {
		super();
		this.id = id;
		this.currentDate = currentDate;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getCurrDate() {
		return currentDate;
	}

	public void setCurrDate(String currentDate) {
		this.currentDate = currentDate;
	}
}
