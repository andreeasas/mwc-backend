package com.mwc.services;

import java.util.List;

import com.mwc.domain.Category;

public interface CategoryService {
	
	public List<Category> getUserSpecific(long userId);
	
	public List<Category> getMemberSpecific(long userId);
	
	public Category getById(long id);
	
	public void saveOrUpdate(Category category);

    public void delete(long id);
    
    public List<Category> findByMemberId(long memberId);

}
