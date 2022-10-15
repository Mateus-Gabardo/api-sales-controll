package com.api.salescontroll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.api.salescontroll.command.Calculate;
import com.api.salescontroll.model.ProductModel;
import com.api.salescontroll.model.SaleItemModel;
import com.api.salescontroll.model.SaleModel;

@SpringBootTest
@AutoConfigureMockMvc
public class SaleControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void saleGetAllTest() throws Exception{
		mockMvc.perform(get("/sale"))
						.andExpect(status().isOk());
	}
	
	@Test
	public void discountCalculateTest() {
		SaleModel sale = new SaleModel();
		sale.setSale_status(1);
		sale.setTax_amount(0.5);
		
		List<SaleItemModel> itens = new ArrayList<SaleItemModel>();
		
		ProductModel p = new ProductModel();		
		p.setActive(1);
		p.setCategory(1);
		p.setPrice(5);
		
		SaleItemModel oneItem = new SaleItemModel();
		oneItem.setPrice_per_unity(5.0);
		oneItem.setQuantity(2);		
		oneItem.setProduct(p);
		
		itens.add(oneItem);
		
		double expect = 5.0;
		Calculate.applyDiscountInSale(sale, itens);		
		assertEquals(expect, sale.getSale_amount_paid());
	}
}
