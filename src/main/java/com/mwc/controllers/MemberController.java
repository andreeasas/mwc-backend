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
import org.springframework.web.servlet.ModelAndView;

import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.services.MemberService;
import com.mwc.services.MonetaryUnitService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MonetaryUnitService monetaryUnitService;

    @RequestMapping(value = "/manageUser", method = RequestMethod.GET)
    public ModelAndView welcome(Model model, HttpServletRequest request) {
    	
    	User user = (User)request.getSession().getAttribute("authUser");
    	List<Member> members = memberService.getAllByUserId(user.getId());
    	
    	List<String> currenciesCodes = monetaryUnitService.findAllCurrenciesCodes();
    	
    	ModelAndView modelAndView = new ModelAndView("members");
    	modelAndView.addObject("members", members);
    	modelAndView.addObject("currencyCodes", currenciesCodes);
		
		return modelAndView;
    }
    
	
}
