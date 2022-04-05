package com.nvhungf.dao;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.nvhungf.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, String>{
	
	@Query("SELECT o FROM Customer o WHERE o.fullname LIKE ?1")
	List<Customer> findByKeywords(String keywords);
	
	@Query("SELECT o.password FROM Customer o WHERE o.email LIKE ?1")
	String findPass(String keywords);
}
