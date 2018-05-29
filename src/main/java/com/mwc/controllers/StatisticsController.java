package com.mwc.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.mwc.domain.User;
import com.mwc.dto.CategoryCostTotalDto;
import com.mwc.services.CostService;

@Controller
public class StatisticsController {

	@Autowired
	private CostService costService;
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(Model model, HttpServletRequest request) {
		model.addAttribute("totalExpenses", new ArrayList<CategoryCostTotalDto>());
		
		return "statistics";
	}
	
	@RequestMapping(value = "/showExpenses", method = RequestMethod.GET)
    public String  showExpenses(Model model, HttpServletRequest request) {
        
		User user = (User)request.getSession().getAttribute("authUser");
		
    	Date startDate = new GregorianCalendar(2018, Calendar.JANUARY, 18).getTime();
		Date endDate = new GregorianCalendar(2018, Calendar.JUNE, 20).getTime();
		
		List<CategoryCostTotalDto> totalExpenses = costService.findTotalExpenseByUserInPeriod(user, startDate, endDate);
		
		model.addAttribute("totalExpenses", totalExpenses);
		
        return "statistics";
    }
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/showStatistics", method = RequestMethod.GET)
    public AjaxResponseBody  showStatistics(HttpServletRequest request) {
        
		@SuppressWarnings("rawtypes")
//		String name = (String)((LinkedHashMap)json).get("name");
		
		User user = (User)request.getSession().getAttribute("authUser");
		
    	Date startDate = new GregorianCalendar(2018, Calendar.JANUARY, 18).getTime();
		Date endDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 20).getTime();
		
		List<CategoryCostTotalDto> totalExpenses = costService.findTotalExpenseByUserInPeriod(user, startDate, endDate);
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();		
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(totalExpenses);
		
        return ajaxResponse;
    }
	
}
