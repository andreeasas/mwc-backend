package com.mwc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    	model.addAttribute("userSpecific", userCategories);

        return "categories";
    }
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String registrationPost(Model model) {
        model.addAttribute("userForm", new User());

        return "categories";
    }
}
