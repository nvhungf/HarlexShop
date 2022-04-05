package com.nvhungf.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Customer")
public class Customer implements Serializable{
	@Id
	String username;
	String password;
	String fullname;
	String address;
	String email;
	String phone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	List<Order> orders;	
	
}
