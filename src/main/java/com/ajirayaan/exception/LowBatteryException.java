package com.ajirayaan.exception;

public class LowBatteryException extends RuntimeException {
	public LowBatteryException() {
		super("Battery is down. Rover cant operate.");
	}
}
