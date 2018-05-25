package com.mwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cost implements Serializable {
	private static final long serialVersionUID = 1L;

	  @Id
	  @Column(name = "ID")
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cost_gen")
	  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen_costs", name = "cost_gen")
	  private long id;

	  @ManyToOne
	  private User dbUser;

	  @ManyToOne
	  private Member member;

	  @ManyToOne
	  private Category category;

	  @ManyToOne
	  private MonetaryUnit um;

	  private double value;

	  @Temporal(TemporalType.DATE)
	  private Date costDate;

	  private String description;

	  public Cost() {
	  }

	  public Cost(User user, Member member, Category category, MonetaryUnit um, Double value, Date costDate, String description) {
	    super();
	    this.dbUser = user;
	    this.member = member;
	    this.category = category;
	    this.um = um;
	    this.value = value;
	    this.costDate = costDate;
	    this.description = description;
	  }

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public User getDbUser() {
		return dbUser;
	}

	public void setDbUser(User dbUser) {
		this.dbUser = dbUser;
	}

	public Member getMember() {
	    return member;
	  }

	  public void setMember(Member member) {
	    this.member = member;
	  }

	  public Category getCategory() {
	    return category;
	  }

	  public void setCategory(Category category) {
	    this.category = category;
	  }

	  public MonetaryUnit getUm() {
	    return um;
	  }

	  public void setUm(MonetaryUnit um) {
	    this.um = um;
	  }

	  public double getValue() {
	    return value;
	  }

	  public void setValue(double value) {
	    this.value = value;
	  }

	  public Date getCostDate() {
	    return costDate;
	  }

	  public void setCostDate(Date costDate) {
	    this.costDate = costDate;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }

	}

