package com.ajirayaan.model;

import java.util.ArrayList;
import java.util.TreeSet;

import com.ajirayaan.exception.RoverOutOfBoundsException;
import com.ajirayaan.exception.StormException;
import com.ajirayaan.utils.Directions;
import com.ajirayaan.utils.Status;

public class Rover {
	private String status = Status.normal.name();
	private int battery;
	private TreeSet<Sample> inventory = new TreeSet<Sample>();
	private int total_capacity = 5;
	private int used_space = 0;
	private Location location;
	private BoundingBox box;

	public void setBox(BoundingBox box) {
		this.box = box;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
		if (Status.immobile.name().equals(status)) {
			status = Status.normal.name();
		}
	}

	public TreeSet<Sample> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Sample> sample) {
		this.inventory.addAll(sample);
		used_space = sample.size();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void move(Direction direction, Environment environment, Configuration configuration) {
		if (Status.immobile.name().equals(status) && environment.isSolar_flare()) {
			setBattery(10);
			status = Status.normal.name();
		}
		if (!configuration.isOperationAllowed("move", status)) {
			throw new RuntimeException();
		}
		int row = location.getRow();
		int col = location.getColumn();
		if (box == null) {
			box = environment.boundingBox();
		}
		switch (Directions.valueOf(direction.getDirection())) {
		case right:
			if (col < box.getColumnUpperBound()) {
				location.setColumn(col + 1);
				battery--;
			} else {
				throw new RoverOutOfBoundsException();
			}
			break;
		case left:
			if (col > box.getColumnLowerBound()) {
				location.setColumn(col - 1);
				battery--;
			} else {
				throw new RoverOutOfBoundsException();
			}
			break;
		case up:
			if (row < box.getRowUpperBound()) {
				location.setRow(row + 1);
				battery--;
			} else {
				throw new RoverOutOfBoundsException();
			}
			break;
		case down:
			if (row > box.getRowLowerBound()) {
				location.setRow(row - 1);
				battery--;
			} else {
				throw new RoverOutOfBoundsException();
			}
			break;
		default:
			throw new RuntimeException("command not supported...");
		}
		environment.setTerrain(location);
	}

	public void collectSample(Sample sample, Configuration configuration) {
		if (!configuration.isOperationAllowed("collect-sample", status)) {
			throw new RuntimeException();
		}
		if (inventory.isEmpty()) {
			inventory.add(sample);
			used_space = sample.getQuantity();
			return;
		}
		int needed_space = sample.getQuantity();
		int available_space = total_capacity - used_space;
		if (needed_space <= available_space) {
			addSample(sample);
		} else {
			int freed_space = 0;
			for (int i = 0; i < needed_space - available_space; i++) {
				Sample temp = inventory.last();
				if (temp.compareTo(sample) > 0) {
					if (decrementSampleQuantity(inventory.last())) {
						freed_space++;
					}
					continue;
				}
			}
			if ((available_space + freed_space) > 0) {
				sample.setQuantity(available_space + freed_space);
				addSample(sample);
			}
		}
	}

	private void addSample(Sample sample) {
		if (inventory.contains(sample)) {
			for (Sample s : inventory) {
				if (s.equals(sample)) {
					s.setQuantity(s.getQuantity() + sample.getQuantity());
					used_space += sample.getQuantity();
				}
			}
		} else {
			inventory.add(sample);
			used_space += sample.getQuantity();
		}
	}

	public void useShield() {
		Sample shield = new Sample();
		shield.setType("storm-shield");
		shield.setQuantity(1);
		shield.setPriority(1);
		useShield(shield);
	}

	public void useShield(Sample shield) {
		if (Status.destroyed.name().equals(status)) {
			throw new RuntimeException("Rover destroed...");
		}
		if (!Status.shielded.name().equals(status)) {
			try {
				if (inventory.isEmpty() || !inventory.contains(shield)) {
					status = Status.destroyed.name();
				}
				if (inventory.contains(shield)) {
					shield = inventory.first();
					decrementSampleQuantity(shield);
					status = Status.shielded.name();
				} else {
					status = Status.destroyed.name();
				}
			} catch (Exception e) {
				status = Status.destroyed.name();
			}
		} else {
			throw new StormException();
		}
	}

	public void unShield() {
		if (!Status.destroyed.name().equals(status) && Status.shielded.name().equals(status)) {
			status = Status.normal.name();
		}
	}

	private boolean decrementSampleQuantity(Sample i) {
		if (i != null) {
			if (i.getQuantity() <= 1) {
				inventory.remove(i);
			} else {
				i.setQuantity(i.getQuantity() - 1);
			}
			used_space--;
			return true;
		}
		return false;
	}

}
