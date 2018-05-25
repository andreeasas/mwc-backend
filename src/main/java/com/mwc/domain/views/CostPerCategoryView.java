package com.mwc.domain.views;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class CostPerCategoryView {

	@Id
	private String categName;
	private double value;

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
	
}
