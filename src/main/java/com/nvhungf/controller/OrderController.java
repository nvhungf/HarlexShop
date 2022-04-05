package com.nvhungf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nvhungf.dao.CustomerDao;
import com.nvhungf.dao.OderDao;
import com.nvhungf.dao.OderDetailDao;
import com.nvhungf.entity.Customer;
import com.nvhungf.entity.Order;
import com.nvhungf.entity.OrderDetail;
import com.nvhungf.service.SessionService;

@Controller
public class OrderController {
	@Autowired
	OderDao dao;
	
	@Autowired
	OderDetailDao daod;

	@Autowired
	SessionService session;

	@RequestMapping("/dashboard/apps_order")
	public String index(Model model) {
		Order item = new Order();
		model.addAttribute("item", item);
		List<Order> items = dao.findAll();
		model.addAttribute("items", items);
		return "dashboard/apps_order";
	}
	@RequestMapping("/apps_order/order-details/{id}")
	public String orderdtails(Model model, @PathVariable("id") String id) {
		List<OrderDetail> items = daod.findOrder(id);
		model.addAttribute("items", items);
		return "dashboard/apps_orderDetails";
	}
}
