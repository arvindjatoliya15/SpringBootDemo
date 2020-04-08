package com.omnicuris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Orders {

	@Id
	@GeneratedValue
	int orderId;
	Integer itemId;
	Integer noOfItems;
	String userEmailId;
	Double price;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(Integer noOfItems) {
		this.noOfItems = noOfItems;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", itemId=" + itemId + ", noOfItems=" + noOfItems + ", userEmailId="
				+ userEmailId + ", price=" + price + "]";
	}

	
}
