package com.mwc.dto;

import java.util.List;

public class TotalStatisticsDto {

	private List<CategoryCostTotalDto> categoryCostSums;
	private double total;
	
	public TotalStatisticsDto() {
	}

	public TotalStatisticsDto(List<CategoryCostTotalDto> categoryCostSums, double total) {
		this.categoryCostSums = categoryCostSums;
		this.total = total;
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
	
}
