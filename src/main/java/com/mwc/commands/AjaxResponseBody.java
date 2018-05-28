package com.mwc.commands;

import com.fasterxml.jackson.annotation.JsonView;
import com.mwc.domain.Category;
import com.mwc.domain.Member;
import com.mwc.domain.User;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	String msg;
	
	@JsonView(Views.Public.class)
	String code;
	
	@JsonView(Views.Public.class)
	Object result;
	
	public AjaxResponseBody() {
	}

	public AjaxResponseBody(String msg, String code, Object result) {
		this.msg = msg;
		this.code = code;
		this.result = result;
	}

	//getters and setters

	public String getCode() {
	    return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
	    return this.msg;
	}
	
	public void setMessage(String msg) {
		this.msg = msg;
	}
	
	public Object getResultData() {
	    return this.result;
	}
	
	public void setResultData(Object resultData) {
		this.result = resultData;
	}
}