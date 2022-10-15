package com.api.salescontroll.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.salescontroll.model.ProductModel;
import com.api.salescontroll.model.SaleItemModel;
import com.api.salescontroll.model.SaleModel;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItemModel, UUID>{
	
	public List<SaleItemModel> findByProduct(ProductModel product);
	public List<SaleItemModel> findBySale(SaleModel sale);
}
