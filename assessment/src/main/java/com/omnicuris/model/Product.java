package com.omnicuris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	int itemId;
	String itemName;
	Integer categoryId;
	Integer availQuantity;
	Integer pricePerUnit;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getAvailQuantity() {
		return availQuantity;
	}
	public void setAvailQuantity(Integer availQuantity) {
		this.availQuantity = availQuantity;
	}
	public Integer getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(Integer pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	@Override
	public String toString() {
		return "Product [itemId=" + itemId + ", itemName=" + itemName + ", categoryId=" + categoryId
				+ ", availQuantity=" + availQuantity + ", pricePerUnit=" + pricePerUnit + "]";
	}
}
