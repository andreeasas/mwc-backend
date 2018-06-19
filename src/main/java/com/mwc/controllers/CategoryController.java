package com.mwc.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.mwc.commands.AjaxResponseBody;
import com.mwc.commands.Views;
import com.mwc.domain.Category;
import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.services.CategoryService;
import com.mwc.services.MonetaryUnitService;


@Controller
public class CategoryController {
	
	@Autowired
    private CategoryService categoryService;
	
	@Autowired
	private MonetaryUnitService monetaryUnitService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView prepareForm(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
        
        User user = (User)request.getSession().getAttribute("authUser");
    	List<Category> userCategories = categoryService.getUserSpecific(user.getId());
    	
    	Member member = (Member)request.getSession().getAttribute("selectedMember");
    	List<Category> memberCategories = categoryService.findByMemberId(member.getId());
    	
		List<String> currenciesCodes = monetaryUnitService.findAllCurrenciesCodes();
		
		modelAndView.addObject("userSpecificCategories", userCategories);
		modelAndView.addObject("memberSpecificCategories", memberCategories);
		modelAndView.addObject("currencyCodes", currenciesCodes);
		modelAndView.setViewName("categories");
		
        return modelAndView;
    }
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public AjaxResponseBody  addCategory(@RequestBody Object json, HttpServletRequest request, HttpServletResponse response) {
        
		@SuppressWarnings("rawtypes")
		String name = (String)((LinkedHashMap)json).get("name");
		@SuppressWarnings("rawtypes")
		String owner = (String)((LinkedHashMap)json).get("owner");
		
		Category category = new Category();
		category.setName(name);
		
		if (owner.equalsIgnoreCase("user")) {
	        User user = (User)request.getSession().getAttribute("authUser");
	        category.setDbUser(user);
	        
	        if(categoryService.findByUserIdAndName(user.getId(), name)!=null) {
	        	return sendErrorMssg(response, 400, "The category wasn't added, a category with this name already exists.");
	        }
		}
		if (owner.equalsIgnoreCase("member")) {
			Member member = (Member)request.getSession().getAttribute("selectedMember");
			category.setMember(member);
			
			if(categoryService.findByMemberIdAndName(member.getId(), name)!=null) {
				return sendErrorMssg(response, 400, "The category wasn't added, a category with this name already exists.");
			}
		}
		
		categoryService.saveOrUpdate(category);
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();		
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(category);
		
        return ajaxResponse;
    }

	private AjaxResponseBody sendErrorMssg(HttpServletResponse response, int errorCode, String mssg) {
		try {
			response.sendError(errorCode, mssg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AjaxResponseBody();
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
		
		// make sure there's not a category with this name already
		if (category.getDbUser()!=null) {
			 if(categoryService.findByUserIdAndName(category.getDbUser().getId(), name) != null) {
		        	return sendErrorMssg(response, 400, "The category wasn't updated, a category with this name already exists.");
		        }
		}
		if (category.getMember()!=null) {
			if(categoryService.findByMemberIdAndName(category.getMember().getId(), name) != null) {
				return sendErrorMssg(response, 400, "The category wasn't updated, a category with this name already exists.");
			}
		}
		
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
        
		try {
			categoryService.delete(id);
		} catch (DataIntegrityViolationException e) {
			try {
				if (e.getMessage().contains("constraint [EXPENSE.FK3EJJORP07RCJ5P4H42G6I1Q1P]")) {
					response.sendError(500, "The category wasn't deleted because it has cost records.");
				} else {
					response.sendError(500, "The category couldn't be deleted because of database constraints.");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(null);
		
        return ajaxResponse;
    }
}
