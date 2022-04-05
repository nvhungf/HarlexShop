package com.nvhungf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nvhungf.entity.OrderDetail;


public interface OderDetailDao extends JpaRepository<OrderDetail, String> {
	@Query(value = "SELECT * FROM  OrderDetails u WHERE u.orderid = ?1", nativeQuery = true)
	List<OrderDetail> findOrder(String id);
}
