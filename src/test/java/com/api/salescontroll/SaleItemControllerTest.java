package com.api.salescontroll;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SaleItemControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void saleItemGetAllTest() throws Exception{
		mockMvc.perform(get("/saleitem?page=1&size=2"))
						.andExpect(status().isOk());
	}
}
