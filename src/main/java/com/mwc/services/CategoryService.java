package com.mwc.services;

import java.util.List;

import com.mwc.domain.Category;

public interface CategoryService {
	List<Category> getAllByUser(long userId);
	List<Category> getUserSpecific(long userId);
	List<Category> getMemberSpecific(long memberId);
	
	Category getById(long id);
	
	Category saveOrUpdate(Category member);

    void delete(long id);
}
