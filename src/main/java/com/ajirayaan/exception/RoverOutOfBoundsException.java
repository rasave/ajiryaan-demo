package com.ajirayaan.exception;

public class RoverOutOfBoundsException extends RuntimeException{
	public RoverOutOfBoundsException() {
        super("Can move only within mapped area");
    }
}
