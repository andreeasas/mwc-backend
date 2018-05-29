package com.mwc.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.mwc.commands.AjaxResponseBody;
import com.mwc.commands.Views;
import com.mwc.domain.Category;
import com.mwc.domain.User;
import com.mwc.services.CategoryService;


@Controller
public class CategoryController {
	
	@Autowired
    private CategoryService categoryService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String registration(Model model, HttpServletRequest request) {
        model.addAttribute("userForm", new User());
        
        User user = (User)request.getSession().getAttribute("authUser");
    	
    	List<Category> userCategories = categoryService.getUserSpecific(user.getId());
    	model.addAttribute("userSpecificCategories", userCategories);
    	
    	List<Category> memberCategories = categoryService.getMemberSpecific(user.getId());
    	model.addAttribute("memberSpecificCategories", memberCategories);

        return "categories";
    }
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public AjaxResponseBody  addCategory(@RequestBody Object json, HttpServletRequest request, HttpServletResponse response) {
        
		@SuppressWarnings("rawtypes")
		String name = (String)((LinkedHashMap)json).get("name");
		User user = (User)request.getSession().getAttribute("authUser");
		
		//use Repository to save category in DB
		Category category = new Category();
		category.setName(name);
		category.setDbUser(user);
		
		categoryService.saveOrUpdate(category);
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();		
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(category);
		
        return ajaxResponse;
    }
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public AjaxResponseBody  updateCategory(@RequestBody Object json, HttpServletRequest request, HttpServletResponse response) {
        
		@SuppressWarnings("rawtypes")
		String id = (String)((LinkedHashMap)json).get("id");
		@SuppressWarnings("rawtypes")
		String name = (String)((LinkedHashMap)json).get("name");
		
		Category category = categoryService.getById(Long.valueOf(id).longValue());
		category.setName(name);
		categoryService.saveOrUpdate(category);
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(name);
		
        return ajaxResponse;
    }
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.DELETE)
    public AjaxResponseBody  deleteCategory(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        
		categoryService.delete(id);		
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(null);
		
        return ajaxResponse;
    }
}
