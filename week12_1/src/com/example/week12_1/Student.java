package com.example.week12_1;

public class Student {
	private String name, number;
	private boolean fullTime;
	private long dbId;

	public Student(String name, String number, boolean fullTime) {
		this.name = name;
		this.number = number;
		this.fullTime = fullTime;
	}
	
	public long getDbId() {
		return dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public boolean isFullTime() {
		return fullTime;
	}
}
