package com.api.salescontroll.command;

import java.util.List;

import com.api.salescontroll.model.SaleItemModel;
import com.api.salescontroll.model.SaleModel;

public class Calculate {
	
	public static SaleModel applyDiscountInSale(SaleModel sale, List<SaleItemModel> salesItens ) {
		double amountProduct = 0;
		double amountService = 0;
		
		for(SaleItemModel item : salesItens) {
			double itemValue = item.getProduct().getPrice() * item.getQuantity();
			if(item.getProduct().getCategory() == 1) {
				amountProduct += itemValue;
			} else {
				amountService += itemValue;
			}
		}
		double paid = (amountService + amountProduct) - (amountProduct * sale.getTax_amount());
		sale.setSale_amount(amountService + amountProduct);
		sale.setSale_amount_paid(paid);
		return sale;
	}
}
