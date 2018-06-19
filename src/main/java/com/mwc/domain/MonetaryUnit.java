package com.mwc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Table(name = "UM")
@Entity
public class MonetaryUnit implements Serializable {
	  private static final long serialVersionUID = 1L;

	  public static final String UM_BY_ID = "User.by.id";

	  @Id
	  private String code;
	  
	  private String description;

	  public MonetaryUnit() {
	  }

	  public MonetaryUnit(String code, String description) {
	    super();
	    this.code = code;
	    this.description = description;
	  }

	  public String getCode() {
	    return code;
	  }

	  public void setCode(String code) {
	    this.code = code;
	  }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	}

