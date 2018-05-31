package com.mwc.dto;

import java.util.List;

public class TotalStatisticsDto {

	private List<CategoryCostTotalDto> categoryCostSums;
	private double total;
	private String currency;
	
	public TotalStatisticsDto() {
	}

	public TotalStatisticsDto(List<CategoryCostTotalDto> categoryCostSums, double total) {
		this.categoryCostSums = categoryCostSums;
		this.total = total;
	}
	
	public TotalStatisticsDto(List<CategoryCostTotalDto> categoryCostSums, double total, String currency) {
		this(categoryCostSums, total);
		this.currency = currency;
	}

	public List<CategoryCostTotalDto> getCategoryCostSums() {
		return categoryCostSums;
	}

	public void setCategoryCostSums(List<CategoryCostTotalDto> categoryCostSums) {
		this.categoryCostSums = categoryCostSums;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
