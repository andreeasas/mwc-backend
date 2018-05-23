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

//	  @OneToMany( //
//	  mappedBy = "um", //
//	  cascade = { CascadeType.ALL } //
//	  )
//	  private Collection<UMDescription> descriptions;

	  public MonetaryUnit() {
	  }

	  public MonetaryUnit(String code) {
	    super();
	    this.code = code;
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
 
//	  public Collection<UMDescription> getDescriptions() {
//	    return descriptions;
//	  }
//
//	  public void setDescriptions(Collection<UMDescription> descriptions) {
//	    this.descriptions = descriptions;
//	  }

	}

