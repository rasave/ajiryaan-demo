package com.ajirayaan.model;

public class BoundingBox {
	private int rowUpperBound;
	private int rowLowerBound;
	private int columnUpperBound;
	private int columnLowerBound;

	public BoundingBox(int rowUpperBound, int rowLowerBound, int columnUpperBound, int columnLowerBound) {
		super();
		this.rowUpperBound = rowUpperBound;
		this.rowLowerBound = rowLowerBound;
		this.columnUpperBound = columnUpperBound;
		this.columnLowerBound = columnLowerBound;
	}

	public int getRowUpperBound() {
		return rowUpperBound;
	}

	public void setRowUpperBound(int rowUpperBound) {
		this.rowUpperBound = rowUpperBound;
	}

	public int getRowLowerBound() {
		return rowLowerBound;
	}

	public void setRowLowerBound(int rowLowerBound) {
		this.rowLowerBound = rowLowerBound;
	}

	public int getColumnUpperBound() {
		return columnUpperBound;
	}

	public void setColumnUpperBound(int columnUpperBound) {
		this.columnUpperBound = columnUpperBound;
	}

	public int getColumnLowerBound() {
		return columnLowerBound;
	}

	public void setColumnLowerBound(int columnLowerBound) {
		this.columnLowerBound = columnLowerBound;
	}

}
