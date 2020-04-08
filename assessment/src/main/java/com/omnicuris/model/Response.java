package com.omnicuris.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_EMPTY)
public class Response implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	int success;
	String message;
	Object data;
	
	public Response() {}
	
	public Response(int success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public Response(int success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
