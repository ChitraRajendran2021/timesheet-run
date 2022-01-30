package com.ikea.timesheet.ikea;

public class Timesheet {

	private long id;

	private String loginTime;

	private String logoutTime;

	private String currentDate;

	public Timesheet() {

	}

	public Timesheet(String loginTime, String logoutTime, String currentDate) {
		super();
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.currentDate = currentDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getCurrDate() {
		return currentDate;
	}

	public void setCurrDate(String currentDate) {
		this.currentDate = currentDate;
	}
}
