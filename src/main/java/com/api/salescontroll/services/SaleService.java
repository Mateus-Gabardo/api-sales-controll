package com.api.salescontroll.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.salescontroll.model.SaleModel;
import com.api.salescontroll.repositories.SaleRepository;


@Service
public class SaleService {
	final SaleRepository saleRepository;

	public SaleService(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}
	
	@Transactional
	public SaleModel save(SaleModel saleModel) {
		return this.saleRepository.save(saleModel);
	}

	public Page<SaleModel> findAll(Pageable peageable) {
		return this.saleRepository.findAll(peageable);
	}
	
	public Optional<SaleModel> findById(UUID id) {
		return this.saleRepository.findById(id);
	}

	public void delete(SaleModel saleModel) {
		this.saleRepository.delete(saleModel);
	}
		
}
