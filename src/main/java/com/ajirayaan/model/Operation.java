package com.ajirayaan.model;

import java.util.Map;

public class Operation {
	private Map<String, Map<String, String>> performs;
	private String is;

	public Map<String, Map<String, String>> getPerforms() {
		return performs;
	}
	public void setPerforms(Map<String, Map<String, String>> performs) {
		this.performs = performs;
	}
	public String getIs() {
		return is;
	}
	public void setIs(String is) {
		this.is = is;
	}

}
