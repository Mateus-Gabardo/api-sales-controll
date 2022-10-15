package com.api.salescontroll.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProductDto {
	@NotBlank
	@Size(max = 150, message = "The description entered exceeds the value allowed for the field")
	private String description;	
	
	@Min(value = 0, message = "It is not allowed to enter values ​​less than 1")
	@Max(value = 3, message = "Enter 1 for Product and 2 for Service")
	private Integer category;
	
	@Min(value = 0, message = "Negative values ​​are not supported")
	private double price;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
