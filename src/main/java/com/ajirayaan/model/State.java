package com.ajirayaan.model;

import java.util.ArrayList;

public class State {
	private String name;
	private ArrayList<String> allowedActions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(ArrayList<String> allowedActions) {
		this.allowedActions = allowedActions;
	}

}
