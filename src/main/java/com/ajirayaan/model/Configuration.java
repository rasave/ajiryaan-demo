package com.ajirayaan.model;

import java.util.ArrayList;
import java.util.Map;

import com.ajirayaan.exception.LowBatteryException;
import com.ajirayaan.exception.StormException;
import com.ajirayaan.utils.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class Configuration {
	private ArrayList<Scenario> scenarios;

	private ArrayList<State> states;

	@JsonProperty("deploy-point")
	@SerializedName("deploy-point")
	private Location deploy_point;

	@JsonProperty("initial-battery")
	@SerializedName("initial-battery")
	private int initial_battery;

	private ArrayList<Sample> sample;

	public boolean chekConditions(Rover rov, Environment environment) {
		for (Scenario scenario : scenarios) {
			for (Condition condition : scenario.getConditions()) {
				switch (scenario.getName()) {
				case "battery-low":
					if (condition.isPassed(rov.getBattery() + "")) {
						rov.setStatus(Status.immobile.name());
					}
					break;
				case "encountering-storm":
					if (condition.isPassed(String.valueOf(environment.isStorm()))) {
						Map<String, String> map = scenario.getRover().get(0).getPerforms().get("item-usage");
						Sample sample = new Sample();
						sample.setType(map.get("type"));
						sample.setQuantity(Integer.valueOf(map.get("qty")));
						sample.setPriority(1);
						rov.useShield(sample);
						throw new StormException();
					}
					break;

				case "encountering-water":
					if (condition.isPassed(environment.getTerrain(rov.getLocation()) + "")) {
						Map<String, String> map = scenario.getRover().get(0).getPerforms().get("collect-sample");
						Sample sample = new Sample();
						sample.setType(map.get("type"));
						sample.setQuantity(Integer.valueOf(map.get("qty")));
						sample.setPriority(2);
						rov.collectSample(sample);
					}
					break;
				case "encountering-rock":
					if (condition.isPassed(environment.getTerrain(rov.getLocation()) + "")) {
						Map<String, String> map = scenario.getRover().get(0).getPerforms().get("collect-sample");
						Sample sample = new Sample();
						sample.setType(map.get("type"));
						sample.setQuantity(Integer.valueOf(map.get("qty")));
						sample.setPriority(3);
						rov.collectSample(sample);
					}
					break;

				}
			}
		}
		return true;
	}

	public boolean equate(String operater, String val1, String val2) {

		switch (operater) {
		case "eq":
			if (val1.equals(val2)) {
				return true;
			}
			break;
		case "ne":
			if (!val1.equals(val2)) {
				return true;
			}
			break;

		case "lte":
			if (Integer.parseInt(val1) <= Integer.parseInt(val2)) {
				return true;
			}
			break;
		case "gte":
			if (Integer.parseInt(val1) >= Integer.parseInt(val2)) {
				return true;
			}
			break;
		case "lt":
			if (Integer.parseInt(val1) < Integer.parseInt(val2)) {
				return true;
			}
			break;

		case "gt":
			if (Integer.parseInt(val1) > Integer.parseInt(val2)) {
				return true;
			}
			break;
		}
		return false;
	}

	public ArrayList<Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(ArrayList<Scenario> scenarios) {
		this.scenarios = scenarios;
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	public Location getDeploy_point() {
		return deploy_point;
	}

	public void setDeploy_point(Location deploy_point) {
		this.deploy_point = deploy_point;
	}

	public int getInitial_battery() {
		return initial_battery;
	}

	public void setInitial_battery(int initial_battery) {
		this.initial_battery = initial_battery;
	}

	public ArrayList<Sample> getInventory() {
		return sample;
	}

	public void setInventory(ArrayList<Sample> sample) {
		this.sample = sample;
	}

}
