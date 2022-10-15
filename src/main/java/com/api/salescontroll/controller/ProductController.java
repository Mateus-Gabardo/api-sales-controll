package com.api.salescontroll.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.salescontroll.dtos.ProductDto;
import com.api.salescontroll.exception.ObjectNotFoundException;
import com.api.salescontroll.model.ProductModel;
import com.api.salescontroll.model.SaleItemModel;
import com.api.salescontroll.services.ProductService;
import com.api.salescontroll.services.SaleItemService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {
	
	final ProductService productService;
	final SaleItemService saleItemService;

	public ProductController(ProductService productService, SaleItemService saleItemService) {
		this.productService = productService;
		this.saleItemService = saleItemService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto){
		
		ProductModel productModel = new ProductModel();
		BeanUtils.copyProperties(productDto, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.save(productModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductModel>> getAllProduct(@PageableDefault(
																page = 0, 
																size = 10, 
																sort = "description", 
																direction = Sort.Direction.ASC)
																Pageable peageable
																){
		return ResponseEntity.status(HttpStatus.OK).body(this.productService.findAll(peageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
		Optional<ProductModel> productModelOptional = this.productService.findById(id);
		if(!productModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageProductNotFound());
		}
		return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletProduct(@PathVariable(value = "id") UUID id) {
		Optional<ProductModel> productModelOptional = this.productService.findById(id);
		if(!productModelOptional.isPresent()) {
			throw new ObjectNotFoundException("Product not found");
		}
		List<SaleItemModel> saleItens = this.saleItemService.findByProduct(productModelOptional.get());
		if(saleItens.size() > 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(getMessageProductConflict());
		}
		this.productService.delete(productModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(getMessageProductDeleted());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
												@RequestBody @Valid ProductDto produtDto){
		
		Optional<ProductModel> productModelOptional = this.productService.findById(id);
		if(!productModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageProductNotFound());
		}
		ProductModel productModel = new ProductModel();
		BeanUtils.copyProperties(produtDto, productModel);
		productModel.setId(productModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(this.productService.save(productModel));
	}
	
	protected String getMessageProductNotFound() {
		return "Product not found";
	}
	
	protected String getMessageProductDeleted() {
		return "Product deleted successfully";
	}
	
	protected String getMessageProductConflict() {
		return "Product deleted successfully";
	}	
	
	
}
