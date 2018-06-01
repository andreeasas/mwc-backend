package com.mwc.controllers;



import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mwc.domain.Cost;
import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.dto.ExpenseDateDto;
import com.mwc.services.CostService;
import com.mwc.services.MemberService;
import com.mwc.services.SecurityService;
import com.mwc.services.UserService;
import com.mwc.validator.UserValidator;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private MemberService memberService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private CostService costService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");}

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");}

        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcome(Model model, HttpServletRequest request) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	User user = userService.findByUsername(authentication.getName());
    	
    	List<Member> members = memberService.getAllByUserId(user.getId());
    	
    	request.getSession().setAttribute("authUser",user);
    	request.getSession().setAttribute("members",members);
    	request.getSession().setAttribute("selectedMember",members.get(0));
    	
		ExpenseDateDto[] expenseDateDtos = costService.findExpensesByUserThisMonth(user.getId(),"EUR");
		
		Gson gson = new Gson();
		String expenseDateJson = gson.toJson(expenseDateDtos);
		
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("expenseDateTuple", expenseDateJson);
		return modelAndView;
    }
    
	@RequestMapping(value = "/switchMember", method = RequestMethod.GET)
    public ModelAndView switchMember(@RequestParam(value="id", required=false) long id,
    		HttpServletRequest request, HttpServletResponse response) {
        
		request.getSession().setAttribute("selectedMember", memberService.getById(id));
		
        return new ModelAndView("welcome");
    }

}
