package com.ajirayaan.exception;

public class StormException extends RuntimeException {
	public StormException() {
		super("Cannot move during a storm");
	}
}
