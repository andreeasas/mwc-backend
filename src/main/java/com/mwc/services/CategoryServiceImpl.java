package com.mwc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwc.domain.Category;
import com.mwc.repositories.CategoryRepository;
import com.mwc.repositories.MemberRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;
	
	@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

	@Override
	public List<Category> getAllByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getUserSpecific(long userId) {
		return categoryRepository.findAllUserSpecific(userId);
	}

	@Override
	public List<Category> getMemberSpecific(long memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category saveOrUpdate(Category member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
