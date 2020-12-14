package com.ajirayaan.model;

public class Condition {
	private String type;
	private String operator;
	private String property;
	private String value;

	public boolean isPassed(String val2) {

		switch (operator) {
		case "eq":
			if (value.equals(val2)) {
				return true;
			}
			break;
		case "ne":
			if (!value.equals(val2)) {
				return true;
			}
			break;
		case "lte":
			if (Integer.parseInt(val2) <= Integer.parseInt(value)) {
				return true;
			}
			break;
		case "gte":
			if (Integer.parseInt(val2) >= Integer.parseInt(value)) {
				return true;
			}
			break;
		case "lt":
			if (Integer.parseInt(val2) < Integer.parseInt(value)) {
				return true;
			}
			break;

		case "gt":
			if (Integer.parseInt(val2) > Integer.parseInt(value)) {
				return true;
			}
			break;
		}
		return false;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
