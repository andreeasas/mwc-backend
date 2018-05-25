package com.mwc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mwc.domain.User;

@Controller
public class CategoryController {

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "categories";
    }
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String registrationPost(Model model) {
        model.addAttribute("userForm", new User());

        return "categories";
    }
}
