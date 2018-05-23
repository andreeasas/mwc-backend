package com.mwc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	  @Id
	  @Column(name = "ID")
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categ_gen")
	  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen_categories", name = "categ_gen")
	  private long id;

	  @Column(nullable = false)
	  private String name;

	  @ManyToOne
	  private Category parent;

	  @OneToMany( //
	  mappedBy = "parent", //
	  cascade = { CascadeType.ALL } //
	  )
	  private List<Category> subcategories;

	  @ManyToOne
	  private User dbUser;

	  @ManyToOne
	  private Member member;

	public Category() {
	  }

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public Category getParent() {
	    return parent;
	  }

	  public void setParent(Category parent) {
	    this.parent = parent;
	  }

	  public List<Category> getSubcategories() {
	    return subcategories;
	  }

	  public void setSubcategories(List<Category> subcategories) {
	    this.subcategories = subcategories;
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

	}

