package com.mwc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mwc.services.CostService;

@Controller
public class StatisticsController {

	@Autowired
	private CostService costService;
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String registration(Model model, HttpServletRequest request) {
		
		return "statistics";
	}
	
}
