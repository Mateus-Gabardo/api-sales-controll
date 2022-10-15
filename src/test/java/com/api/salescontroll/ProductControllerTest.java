package com.api.salescontroll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.api.salescontroll.dtos.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void productGetAllTest() throws Exception{
		mockMvc.perform(get("/product"))
						.andExpect(status().isOk());
	}
	
	@Test
	public void productSaveTest() throws Exception {
		ProductDto productDto = new ProductDto();
		productDto.setCategory(1);
		productDto.setDescription("Milk");
		productDto.setPrice(5.00);
		
		mockMvc.perform(post("/product")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(productDto)))
				.andExpect(status().isCreated());
	}
	
}
