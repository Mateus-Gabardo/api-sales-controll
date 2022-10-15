package com.api.salescontroll.controller;

import java.time.LocalDateTime;
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

import com.api.salescontroll.command.Calculate;
import com.api.salescontroll.dtos.SaleDto;
import com.api.salescontroll.model.SaleItemModel;
import com.api.salescontroll.model.SaleModel;
import com.api.salescontroll.services.SaleItemService;
import com.api.salescontroll.services.SaleService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sale")
public class SaleController {
	final SaleService saleService;
	final SaleItemService saleItemService;

	public SaleController(SaleService saleService, SaleItemService saleItemService) {
		this.saleService = saleService;
		this.saleItemService = saleItemService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveSale(@RequestBody @Valid SaleDto saleDto){	
		SaleModel saleModel = new SaleModel();
		BeanUtils.copyProperties(saleDto, saleModel);
		saleModel.setTime_created(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.saleService.save(saleModel));
	}
	
	@PostMapping("/apply_discount")
	public ResponseEntity<Object> applyDiscount(@PathVariable(value = "tax_amount") double tax_amount,
												@PathVariable(value = "id") UUID id){
		Optional<SaleModel> saleModelOptional = this.saleService.findById(id);
		if(!saleModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSaleNotFound());
		}
		SaleModel sale = saleModelOptional.get();
		List<SaleItemModel> salesItens = this.saleItemService.findBySale(sale);		
		sale.setTax_amount(tax_amount);
		Calculate.applyDiscountInSale(sale, salesItens);
		return ResponseEntity.status(HttpStatus.OK).body(this.saleService.save(sale));
	}
	
	@GetMapping
	public ResponseEntity<Page<SaleModel>> getAllSale(@PageableDefault(
																page = 0, 
																size = 10,
																direction = Sort.Direction.ASC)
																Pageable peageable
																){
		return ResponseEntity.status(HttpStatus.OK).body(this.saleService.findAll(peageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneSale(@PathVariable(value = "id") UUID id) {
		Optional<SaleModel> saleModelOptional = this.saleService.findById(id);
		if(!saleModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSaleNotFound());
		}
		return ResponseEntity.status(HttpStatus.OK).body(saleModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteSale(@PathVariable(value = "id") UUID id) {
		Optional<SaleModel> saleModelOptional = this.saleService.findById(id);
		if(!saleModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSaleNotFound());
		}
		this.saleService.delete(saleModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(getMessageSaleDeleted());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSale(@PathVariable(value = "id") UUID id,
												@RequestBody @Valid SaleDto saleDto){
		
		Optional<SaleModel> saleModelOptional = this.saleService.findById(id);
		if(!saleModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSaleNotFound());
		}
		SaleModel saleModel = new SaleModel();
		BeanUtils.copyProperties(saleDto, saleModel);
		saleModel.setSale_id(saleModelOptional.get().getSale_id());
		saleModel.setItens(saleModelOptional.get().getItens());
		saleModel.setSale_amount(saleModelOptional.get().getSale_amount());
		saleModel.setSale_amount_paid(saleModelOptional.get().getSale_amount_paid());
		saleModel.setTime_created(saleModelOptional.get().getTime_created());
		return ResponseEntity.status(HttpStatus.OK).body(this.saleService.save(saleModel));
	}
	
	protected String getMessageSaleNotFound() {
		return "Sale not found";
	}
	
	protected String getMessageSaleDeleted() {
		return "Sale deleted successfully";
	}

}
