package com.nvhungf.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.nvhungf.entity.Customer;
import com.nvhungf.entity.Product;
import com.nvhungf.entity.Report;


public interface ProductDao extends JpaRepository<Product, String>{
	
	@Query("SELECT o FROM Product o WHERE o.id = ?1")
	List<Product> findDetails(String keywords);
	
	@Query(value = "SELECT * FROM Product u WHERE u.categoryId = ?1", nativeQuery = true)
	List<Product> findByCate(String id);
	
	@Query(value = "SELECT * FROM Product WHERE price BETWEEN ?1 AND ?2", nativeQuery =true )
	List<Product> findByPrice(double minPrice, double maxPrice);
	
	
	@Query(value = "SELECT TOP 10 * FROM Product u ", nativeQuery = true)
	List<Product> findFproduct();
	
	@Query(value = "SELECT TOP 10 * FROM Product u WHERE u.sale = 1", nativeQuery = true)
	List<Product> findSale();
	
	
	 @Query("SELECT new Report(o.category, sum(o.price), count(o)) FROM Product o " + " GROUP BY o.category" + " ORDER BY sum(o.price) DESC")
	 List<Report> getInventoryByCategory();
	 
}
