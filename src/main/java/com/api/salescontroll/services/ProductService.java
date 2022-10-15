package com.api.salescontroll.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.api.salescontroll.model.ProductModel;
import com.api.salescontroll.repositories.ProductRepository;

@Service
public class ProductService {
	final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Transactional
	public ProductModel save(ProductModel product) {
		return this.productRepository.save(product);
	}

	public Page<ProductModel> findAll(Pageable pageable) {
		return this.productRepository.findAll(pageable);
	}

	public Optional<ProductModel> findById(UUID id) {
		return this.productRepository.findById(id);
	}

	public void delete(ProductModel productModel) {
		this.productRepository.delete(productModel);
	}
	
}
