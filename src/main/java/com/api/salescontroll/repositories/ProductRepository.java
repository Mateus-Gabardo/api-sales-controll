package com.api.salescontroll.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.api.salescontroll.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepositoryImplementation<ProductModel, UUID>{
	
}
