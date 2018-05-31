package com.mwc.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.mwc.commands.Views;
import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.dto.CategoryCostTotalDto;
import com.mwc.dto.TotalStatisticsDto;
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
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/showStatistics", method = RequestMethod.POST)
    public ModelAndView  showStatistics(Model model, HttpServletRequest request, @RequestBody Object json) {
        
		@SuppressWarnings("rawtypes")
		String start = (String)((LinkedHashMap)json).get("start_date");
		@SuppressWarnings("rawtypes")
		String end = (String)((LinkedHashMap)json).get("end_date");
		@SuppressWarnings("rawtypes")
		String statisticsType = (String)((LinkedHashMap)json).get("statistics_type");
		
		User user = (User)request.getSession().getAttribute("authUser");
		Member member = (Member) request.getSession().getAttribute("selectedMember");
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = format.parse(start);
			endDate = format.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<TotalStatisticsDto> totalStatisticsDtos = costService.findTotalExpensesWithCurrencyInPeriod(user, member, startDate, endDate, statisticsType);
		
		model.addAttribute("statistics", totalStatisticsDtos);
        return new ModelAndView("parts/statisticsTable");
    }
	
}
