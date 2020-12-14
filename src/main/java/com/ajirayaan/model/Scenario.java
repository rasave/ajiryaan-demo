package com.ajirayaan.model;

import java.util.ArrayList;

public class Scenario {
	private String name;
	private ArrayList<Condition> conditions;
	ArrayList<Operation> rover;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(ArrayList<Condition> conditions) {
		this.conditions = conditions;
	}

	public ArrayList<Operation> getRover() {
		return rover;
	}

	public void setRover(ArrayList<Operation> rover) {
		this.rover = rover;
	}

}
