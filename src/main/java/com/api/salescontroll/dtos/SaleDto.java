package com.api.salescontroll.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class SaleDto {
	@Min(value = 0, message = "The discount percentage cannot be negative")
	private double tax_amount;
	
	@Min(value = 1, message = "unduported status")
	private Integer sale_status;

	public double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(double tax_amount) {
		this.tax_amount = tax_amount;
	}

	public Integer getSale_status() {
		return sale_status;
	}

	public void setSale_status(Integer sale_status) {
		this.sale_status = sale_status;
	}
	
	
}
