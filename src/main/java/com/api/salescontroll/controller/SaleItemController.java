package com.api.salescontroll.controller;

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

import com.api.salescontroll.dtos.SaleItemDto;
import com.api.salescontroll.model.ProductModel;
import com.api.salescontroll.model.SaleItemModel;
import com.api.salescontroll.model.SaleModel;
import com.api.salescontroll.services.ProductService;
import com.api.salescontroll.services.SaleItemService;
import com.api.salescontroll.services.SaleService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/saleitem")
public class SaleItemController {
	
	final SaleItemService saleItemService;
	final ProductService productService;
	final SaleService saleService;

	public SaleItemController(SaleItemService saleItemService, ProductService productService, SaleService saleService) {
		this.saleItemService = saleItemService;
		this.productService = productService;
		this.saleService = saleService;
	}

	@PostMapping
	public ResponseEntity<Object> saveSaleItem(@RequestBody @Valid SaleItemDto saleItemDto){	
		SaleItemModel saleItemModel = new SaleItemModel();
		BeanUtils.copyProperties(saleItemDto, saleItemModel);
		Optional<ProductModel> product = this.productService.findById(saleItemDto.getItem_id());
		Optional<SaleModel> sale = this.saleService.findById(saleItemDto.getSale_id());
		
		if(product.isPresent() && product.get().getActive() == 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(getMessageProductDesatived());
		}
		saleItemModel.setProduct(product.get());
		saleItemModel.setSale(sale.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.saleItemService.save(saleItemModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<SaleItemModel>> getAllProduct(@PageableDefault(
																page = 0, 
																size = 10, 
																direction = Sort.Direction.ASC)
																Pageable peageable
																){
		return ResponseEntity.status(HttpStatus.OK).body(this.saleItemService.findAll(peageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneSaleItem(@PathVariable(value = "id") UUID id) {
		Optional<SaleItemModel> saleItemModelOptional = this.saleItemService.findById(id);
		if(!saleItemModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSaleItemNotFound());
		}
		return ResponseEntity.status(HttpStatus.OK).body(saleItemModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletSaleItem(@PathVariable(value = "id") UUID id) {
		Optional<SaleItemModel> saleItemModelOptional = this.saleItemService.findById(id);
		if(!saleItemModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSaleItemNotFound());
		}
		this.saleItemService.delete(saleItemModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(getMessageSaleItemDeleted());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSaleItem(@PathVariable(value = "id") UUID id,
												 @RequestBody @Valid SaleItemDto saleItemDto){
		
		Optional<SaleItemModel> productModelOptional = this.saleItemService.findById(id);
		if(!productModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSaleItemNotFound());
		}
		SaleItemModel saleItemModel = new SaleItemModel();
		BeanUtils.copyProperties(saleItemDto, saleItemModel);
		saleItemModel.setSale_item_id(productModelOptional.get().getSale_item_id());
		return ResponseEntity.status(HttpStatus.OK).body(this.saleItemService.save(saleItemModel));
	}
	
	protected String getMessageSaleItemNotFound() {
		return "Sale item not found";
	}
	
	protected String getMessageSaleItemDeleted() {
		return "Sale item deleted successfully";
	}
	
	protected String getMessageProductDesatived() {
		return "It is not possible to insert a deactivated product in an sale";
	}

}
