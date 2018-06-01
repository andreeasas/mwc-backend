package com.mwc.dto;

public class ExpenseDateDto {

	private long dateMili;
	private double expenseValue;
	
	public ExpenseDateDto() {
	}

	public ExpenseDateDto(long dateMili, double expenseValue) {
		this.dateMili = dateMili;
		this.expenseValue = expenseValue;
	}

	public long getDateMili() {
		return dateMili;
	}

	public void setDateMili(long dateMili) {
		this.dateMili = dateMili;
	}

	public double getExpenseValue() {
		return expenseValue;
	}

	public void setExpenseValue(double expenseValue) {
		this.expenseValue = expenseValue;
	}
	
}
