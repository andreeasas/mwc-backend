package com.mwc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Member implements Serializable {
	  private static final long serialVersionUID = 1L;

	  @Id
	  @Column(name = "ID")
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_gen")
	  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen_members", name = "member_gen")
	  private long id;
	  private String name;

	  @ManyToOne
	  @JoinColumn(nullable = false)
	  private User dbUser;

	  @OneToMany(mappedBy = "member")
	  private List<Category> categories;

	  public Member() {
	    super();
	  }

	  public Member(String name) {
	    this.name = name;
	  }

	  public Member(String name, User dbUser) {
	    this(name);
	    this.dbUser = dbUser;
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

	  public User getDbUser() {
	    return dbUser;
	  }

	  public void setDbUser(User dbUser) {
	    this.dbUser = dbUser;
	  }

	  public List<Category> getCategories() {
	    return categories;
	  }

	  public void setCategories(List<Category> categories) {
	    this.categories = categories;
	  }

	}
