package com.nvhungf.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String id;
	float price;
	Integer quantity;
	float total;
	@ManyToOne
	@JoinColumn(name = "productid")
	Product product;
	@ManyToOne
	@JoinColumn(name = "orderid")
	Order order;
}
