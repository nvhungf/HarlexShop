package com.nvhungf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvhungf.entity.Order;

public interface OderDao extends JpaRepository<Order, String> {

}
