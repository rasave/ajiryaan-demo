package com.ajirayaan.repository;

import org.springframework.stereotype.Component;

import com.ajirayaan.model.Configuration;
import com.ajirayaan.model.Direction;
import com.ajirayaan.model.Environment;
import com.ajirayaan.model.Rover;

@Component
public class Ajiryaan {
	private Environment environment;
	private Rover rover;
	private Configuration configuration;

	public void configureRover(Configuration configuration) {
		this.configuration = configuration;
		if (rover == null) {
			rover = new Rover();
		}
		if (configuration.getInitial_battery() != 0) {
			rover.setBattery(configuration.getInitial_battery());
		}
		if (configuration.getDeploy_point() != null) {
			rover.setLocation(configuration.getDeploy_point());
			environment.setTerrain(rover.getLocation());
		}
		if (configuration.getInventory() != null) {
			rover.setInventory(configuration.getInventory());
		}
	}

	public void updateEnv(Environment update) {

		if (update.getTemperature() != null) {
			environment.setTemperature(update.getTemperature());
		}
		if (update.isStorm() != null) {
			if (update.isStorm()) {
				rover.useShield();
			}
			environment.setStorm(update.isStorm());
		}
		if (update.isSolar_flare() != null) {
			if (update.isSolar_flare()) {
				rover.setBattery(10);
			} else {
				rover.unShield();
			}
			environment.setSolar_flare(update.isSolar_flare());
		}
	}

	public void configureEnv(Environment environment) {
		if (rover == null) {
			rover = new Rover();
		}
		this.environment = environment;
		rover.setBox(environment.boundingBox());
	}

	public void moveRover(Direction direction) {
		configuration.chekConditions(rover, environment);
		rover.move(direction, environment);
	}

	public Ajiryaan() {
		this.rover = new Rover();
	}

	public Environment getEnvironment() {
		return environment;
	}

	public Rover getRover() {
		return rover;
	}

	public void setRover(Rover rover) {
		this.rover = rover;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
