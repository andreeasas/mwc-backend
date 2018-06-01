package com.mwc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwc.domain.Category;
import com.mwc.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getUserSpecific(long userId) {
		return categoryRepository.findAllUserSpecific(userId);
	}

	@Override
	public List<Category> getMemberSpecific(long userId) {
		return categoryRepository.findAllMemberSpecific(userId);
	}

	@Override
	public Category getById(long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void saveOrUpdate(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void delete(long id) {
		categoryRepository.delete(categoryRepository.findById(id));
	}

	@Override
	public List<Category> findByMemberId(long memberId) {
		return categoryRepository.findByMemberId(memberId);
	}

}
