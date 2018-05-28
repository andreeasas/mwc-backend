package com.mwc.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.mwc.commands.AjaxResponseBody;
import com.mwc.commands.Views;
import com.mwc.domain.Category;
import com.mwc.domain.User;
import com.mwc.services.CategoryService;
import com.mwc.services.MemberService;
import com.mwc.services.UserService;

@Controller
public class CategoryController {
	@Autowired
    private UserService userService;
	@Autowired
    private CategoryService categoryService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String registration(Model model, HttpServletRequest request) {
        model.addAttribute("userForm", new User());
        
//    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    	User user = userService.findByUsername(authentication.getName());
        
        User user = (User)request.getSession().getAttribute("authUser");
    	
    	List<Category> userCategories = categoryService.getUserSpecific(user.getId());
    	model.addAttribute("userSpecificCategories", userCategories);

        return "categories";
    }
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String registrationPost(Model model) {
        model.addAttribute("userForm", new User());

        return "categories";
    }
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public AjaxResponseBody  addCategory(@RequestBody Object json, HttpServletRequest request, HttpServletResponse response) {
        
		String name = (String)((LinkedHashMap)json).get("name");
		
		//use Repository to save category in DB
		
		
		Category category = new Category();
		category.setId(10);
		category.setName(name);
		
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
        
		String id = (String)((LinkedHashMap)json).get("id");
		String name = (String)((LinkedHashMap)json).get("name");
		
		//use Repository to update category in DB
		
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
        
		String test = request.getParameter("id");
		
		//use Repository to delete category in DB
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(null);
		
        return ajaxResponse;
    }
}
