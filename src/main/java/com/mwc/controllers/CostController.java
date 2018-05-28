package com.mwc.controllers;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.mwc.commands.AjaxResponseBody;
import com.mwc.commands.Views;
import com.mwc.domain.Category;
import com.mwc.domain.Cost;


@Controller
public class CostController {
//	@Autowired
//    private CostService costService;
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/addCost/{id}", method = RequestMethod.POST)
    public AjaxResponseBody  addCost(@PathVariable long id, @RequestBody Object json, HttpServletRequest request, HttpServletResponse response) {
        
		String amount = (String)((LinkedHashMap)json).get("amount");
		
		//use Repository to save cost in DB
		
		
		Cost cost = new Cost();
		cost.setId(10);
		cost.setDescription("test");
		cost.setValue(10.5);
		
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();		
		ajaxResponse.setCode("1");
		ajaxResponse.setMessage("OK");
		ajaxResponse.setResultData(cost);
		
        return ajaxResponse;
    }
}
