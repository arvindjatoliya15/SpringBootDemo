package com.omnicuris.model;

import java.io.Serializable;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_EMPTY)
public class OrderRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String userEmailId;
	ArrayList<Orders> orderList;
	
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public ArrayList<Orders> getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList<Orders> orderList) {
		this.orderList = orderList;
	}
	
	
}
