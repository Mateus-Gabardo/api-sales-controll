package com.api.salescontroll.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.salescontroll.model.SaleModel;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, UUID>{
}
