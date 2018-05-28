package com.mwc.services;

import java.util.List;

import com.mwc.domain.Category;
import com.mwc.domain.Cost;

public interface CategoryService {
	
	public List<Category> getUserSpecific(long userId);
	
	public List<Category> getMemberSpecific(long userId);
	
	public Category getById(long id);
	
	public void saveOrUpdate(Category category);

    public void delete(long id);

}
