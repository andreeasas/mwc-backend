package com.mwc.dto;

public class CategoryCostTotalDto {
	
	private String categName;
	private double value;
	private double percentFromTotal;
	private String currency;
	
	public CategoryCostTotalDto() {
	}
	
	public String getCategName() {
		return categName;
	}
	
	public void setCategName(String categName) {
		this.categName = categName;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getPercentFromTotal() {
		return percentFromTotal;
	}
	
	public void setPercentFromTotal(double percentFromTotal) {
		this.percentFromTotal = percentFromTotal;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
