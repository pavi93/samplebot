package com.xebia.robot.model;

public class Bot {

	private int battery;
	private String message;
	
	
	public Bot(int battery, String message) {
		super();
		this.battery = battery;
		this.message = message;
	}
	
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
