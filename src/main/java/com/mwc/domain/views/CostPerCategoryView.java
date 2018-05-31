package com.mwc.domain.views;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.Immutable;


@Entity
@Immutable
public class CostPerCategoryView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CostCategId costCategId;
	
	private double value;
	
	public CostPerCategoryView() {
	}

	public CostPerCategoryView(String categName, String currency) {
		CostCategId costcategId = new CostCategId();
		costcategId.setCategName(categName);
		costcategId.setCurrency(currency);
		this.costCategId = costcategId;
	}

	public CostCategId getCostCategId() {
		return costCategId;
	}

	public void setCostCategId(CostCategId costCategId) {
		this.costCategId = costCategId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	@Embeddable
	static class CostCategId implements Serializable {
		private static final long serialVersionUID = 1L;
		
		protected String categName;
		protected String currency;
		
		protected CostCategId() {
		}

		protected CostCategId(String categName, String currency) {
			this.categName = categName;
			this.currency = currency;
		}

		public String getCategName() {
			return categName;
		}

		public void setCategName(String categName) {
			this.categName = categName;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}
		
	}
	
}

