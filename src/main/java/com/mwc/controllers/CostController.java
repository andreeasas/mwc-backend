package com.mwc.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.mwc.commands.AjaxResponseBody;
import com.mwc.commands.Views;
import com.mwc.domain.Category;
import com.mwc.domain.Cost;
import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.dto.TotalStatisticsDto;
import com.mwc.services.CategoryService;
import com.mwc.services.CostService;
import com.mwc.services.MonetaryUnitService;


@Controller
public class CostController {
	
	@Autowired
    private CostService costService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MonetaryUnitService monetaryUnitService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/addCost/{id}", method = RequestMethod.POST)
    public AjaxResponseBody  addCost(@PathVariable long id, @RequestBody Object json, HttpServletRequest request, HttpServletResponse response) {
		
		String amount = (String)((LinkedHashMap)json).get("amount");
		String description = (String)((LinkedHashMap)json).get("description");
		String currencyCode = (String)((LinkedHashMap)json).get("currency_code");
		String costDate = (String)((LinkedHashMap)json).get("cost_date");
		
		Cost cost = new Cost();
		cost.setDescription(description);
		cost.setValue(Long.valueOf(amount).longValue());
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(costDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cost.setCostDate(date);
		
		Category costCateg = categoryService.getById(id);
		cost.setCategory(costCateg);
		cost.setDbUser(costCateg.getDbUser());
		cost.setMember(costCateg.getMember());
		
		cost.setUm(monetaryUnitService.getByCode(currencyCode));
		
		costService.saveOrUpdate(cost);
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();		
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(cost);
		
        return ajaxResponse;
    }
	
	@RequestMapping(value = "/expenses", method = RequestMethod.GET)
    public ModelAndView showExpenses(Model model, HttpServletRequest request) {
        
		User user = (User)request.getSession().getAttribute("authUser");
		Member member = (Member) request.getSession().getAttribute("selectedMember");
		
		List<List<Cost>> userCostsThisMonth = costService.getAllCostsByUserThisMonth(user);
		List<List<Cost>> memberCostsThisMonth = costService.getAllCostsByMemberThisMonth(member);
		
		ModelAndView modelAndView = new ModelAndView("expenses");
		modelAndView.addObject("userCostsThisMonth",userCostsThisMonth);
		modelAndView.addObject("memberCostsThisMonth", memberCostsThisMonth);
		return modelAndView;
    }
}
