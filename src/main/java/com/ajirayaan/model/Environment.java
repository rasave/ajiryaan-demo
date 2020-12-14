package com.ajirayaan.model;

import com.google.gson.annotations.SerializedName;

public class Environment {
	private Integer temperature;

	private int humidity;

	@SerializedName("solar-flare")
	private Boolean solar_flare;

	private Boolean storm;

	@SerializedName("area-map")
	private transient String[][] area_map;

	private String terrain;

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(Location location) {
		this.terrain = getTerrain(location);
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public Boolean isSolar_flare() {
		return solar_flare;
	}

	public void setSolar_flare(Boolean solar_flare) {
		this.solar_flare = solar_flare;
	}

	public Boolean isStorm() {
		return storm;
	}

	public void setStorm(Boolean storm) {
		this.storm = storm;
	}

	public void setArea_map(String[][] area_map) {
		this.area_map = area_map;
	}

	public BoundingBox boundingBox() {
		BoundingBox box = new BoundingBox(area_map.length - 1, 0, area_map[0].length - 1, 0);
		return box;
	}

	public String getTerrain(Location location) {
		String currentTerrian = area_map[location.getRow()][location.getColumn()];
		return currentTerrian;
	}
}
