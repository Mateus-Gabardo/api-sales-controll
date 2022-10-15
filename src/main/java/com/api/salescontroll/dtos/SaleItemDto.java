package com.api.salescontroll.dtos;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.NotFound;

public class SaleItemDto {
	@Max(value = 100, message = "Cannot add more than 100 items")
	private Integer quantity = 1;	
	@Min(value = 0, message = "Um value must be greater than 0.0")
	private double price_per_unity;
	@NotFound
	private UUID item_id;
	@NotFound
	private UUID sale_id;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getPrice_per_unity() {
		return price_per_unity;
	}
	public void setPrice_per_unity(double price_per_unity) {
		this.price_per_unity = price_per_unity;
	}
	public UUID getItem_id() {
		return item_id;
	}
	public void setItem_id(UUID item_id) {
		this.item_id = item_id;
	}
	public UUID getSale_id() {
		return sale_id;
	}
	public void setSale_id(UUID sale_id) {
		this.sale_id = sale_id;
	}

}
