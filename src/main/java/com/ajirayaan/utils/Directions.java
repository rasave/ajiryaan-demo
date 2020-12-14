package com.ajirayaan.utils;

public enum Directions {
	left("left"), right("right"), up("up"), down("down");

	private String direction;

	private Directions(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
