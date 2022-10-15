package com.api.salescontroll.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbSale")
public class SaleModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column()
	private LocalDateTime time_created;	
	
	@Column()
	private double sale_amount;
	
	@Column()
	private double sale_amount_paid;	
	
	@Column()
	private double tax_amount;
	
	@Column(length = 1, nullable = false)
	private Integer sale_status;
	
	@OneToMany(mappedBy = "sale")
	private List<SaleItemModel> itens;

	public UUID getSale_id() {
		return id;
	}

	public void setSale_id(UUID sale_id) {
		this.id = sale_id;
	}

	public LocalDateTime getTime_created() {
		return time_created;
	}

	public void setTime_created(LocalDateTime time_created) {
		this.time_created = time_created;
	}

	public double getSale_amount() {
		return sale_amount;
	}

	public void setSale_amount(double sale_amount) {
		this.sale_amount = sale_amount;
	}

	public double getSale_amount_paid() {
		return sale_amount_paid;
	}

	public void setSale_amount_paid(double sale_amount_paid) {
		this.sale_amount_paid = sale_amount_paid;
	}

	public double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(double tax_amount) {
		this.tax_amount = tax_amount;
	}

	public Integer getSale_status() {
		return sale_status;
	}	

	public void setSale_status(Integer sale_status) {
		this.sale_status = sale_status;
	}

	public List<SaleItemModel> getItens() {
		return itens;
	}

	public void setItens(List<SaleItemModel> itens) {
		this.itens = itens;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
