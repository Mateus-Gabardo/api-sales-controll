package com.api.salescontroll.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.salescontroll.model.ProductModel;
import com.api.salescontroll.model.SaleItemModel;
import com.api.salescontroll.model.SaleModel;
import com.api.salescontroll.repositories.SaleItemRepository;

@Service
public class SaleItemService {
	final SaleItemRepository saleItemRepository;

	public SaleItemService(SaleItemRepository saleItemRepository) {
		this.saleItemRepository = saleItemRepository;
	}
	
	@Transactional
	public SaleItemModel save(SaleItemModel saleItemModel) {
		return this.saleItemRepository.save(saleItemModel);
	}

	public Page<SaleItemModel> findAll(Pageable pageable) {
		return this.saleItemRepository.findAll(pageable);
	}

	public Optional<SaleItemModel> findById(UUID id) {
		return this.saleItemRepository.findById(id);
	}					

	public void delete(SaleItemModel saleItemModel) {
		this.saleItemRepository.delete(saleItemModel);
	}
	
	public List<SaleItemModel> findByProduct(ProductModel product) {
		return this.saleItemRepository.findByProduct(product);
	}
	
	public List<SaleItemModel> findBySale(SaleModel sale) {
		return this.saleItemRepository.findBySale(sale);
	}
	
	
}
