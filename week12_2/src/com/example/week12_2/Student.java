package com.example.week12_2;

public class Student {
	private String name, number;
	private boolean fullTime;
	private long dbId;

	public Student(String name, String number, boolean fullTime) {
		this.name = name;
		this.number = number;
		this.fullTime = fullTime;
	}
	
	public Student(String name, String number, boolean fullTime, long dbId) {
		super();
		this.name = name;
		this.number = number;
		this.fullTime = fullTime;
		this.dbId = dbId;
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
