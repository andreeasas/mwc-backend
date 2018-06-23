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
import com.mwc.domain.Member;
import com.mwc.domain.MonetaryUnit;
import com.mwc.domain.User;
import com.mwc.services.MemberService;
import com.mwc.services.MonetaryUnitService;
import com.mwc.services.UserService;

@Controller
public class ManageUserController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserService userService;
	
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
    
    @SuppressWarnings("rawtypes")
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/changeCurrency", method = RequestMethod.POST)
    public AjaxResponseBody changeUserCurrency(Model model, HttpServletRequest request, @RequestBody Object json) {
		
    	String currencyCode = (String)((LinkedHashMap)json).get("currency_code");
		MonetaryUnit currency = monetaryUnitService.getByCode(currencyCode);
		
		User user = (User)request.getSession().getAttribute("authUser");
		user.setDefaultCurrency(currency);
		userService.update(user);
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();		
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(user);
		
        return ajaxResponse;
    }
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public AjaxResponseBody  addMember(@RequestBody Object json, HttpServletRequest request, HttpServletResponse response) {
        
		String name = (String)((LinkedHashMap)json).get("name");
		
		Member member = new Member();
		member.setName(name);
		member.setUser((User)request.getSession().getAttribute("authUser"));
		
		memberService.saveOrUpdate(member);
			
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();		
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(member);
		
        return ajaxResponse;
    }
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/deleteMember/{id}", method = RequestMethod.DELETE)
    public AjaxResponseBody  deleteMember(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        
		try {
			memberService.delete(id);
		} catch (DataIntegrityViolationException e) {
			try {
				if (e.getMessage().contains("constraint [EXPENSE.FK3EJJORP07RCJ5P4H42G6I1Q1P]")) { // !!!!
					response.sendError(500, "The member wasn't deleted because it has categories.");
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
    
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
    public AjaxResponseBody  updateCategory(@RequestBody Object json, HttpServletRequest request, HttpServletResponse response) {
        
		String id = (String)((LinkedHashMap)json).get("id");
		String name = (String)((LinkedHashMap)json).get("name");
		
		Member member = memberService.getById(Long.valueOf(id).longValue());
		member.setName(name);
		
		memberService.saveOrUpdate(member);
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(name);
		
        return ajaxResponse;
    }
	
}
