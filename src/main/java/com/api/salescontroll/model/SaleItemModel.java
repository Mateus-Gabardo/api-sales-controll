package com.api.salescontroll.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbSaleItem")
public class SaleItemModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, length = 100)
	private Integer quantity = 1;	
	
	@Column(nullable = false)
	private double price_per_unity;	
	
	@Column()
	private double price;	
	
	@ManyToOne
	@JoinColumn(name = "sale_id")
	@JsonIgnore
	private SaleModel sale;	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductModel product;

	public UUID getSale_item_id() {
		return id;
	}

	public void setSale_item_id(UUID sale_item_id) {
		this.id = sale_item_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getPrice_per_unity() {
		return price_per_unity;
	}

	public void setPrice_per_unity(double price_per_unity) {
		this.price_per_unity = price_per_unity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public SaleModel getSale() {
		return sale;
	}

	public void setSale(SaleModel sale) {
		this.sale = sale;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		if(product.getActive() != 0) {
			this.product = product;			
		} else {
			throw new HttpClientErrorException(HttpStatus.CONFLICT);			
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
