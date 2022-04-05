package com.nvhungf.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@NamedQuery(
		name = "findByKeyword",
		query = "SELECT p FROM Product p WHERE p.name LIKE ?1"
)
@Data
@Entity @Table(name = "Product")
public class Product implements Serializable  {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String id;
	String name;
	String image;
	String image2;
	float price;
	String pricesale;
	String description;
	String width;
	String color;
	String stone;
	String weight;
	String Leght;
	int Quantity = 1;
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderdetails;
}
