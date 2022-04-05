package com.nvhungf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.nvhungf.dao.CustomerDao;
import com.nvhungf.entity.Customer;
import com.nvhungf.service.SessionService;

@Controller
public class CustomerController {
	@Autowired
	CustomerDao dao;
	
	@Autowired
	SessionService session;
	
	//customer
	@RequestMapping("/dashboard/apps_customers")
	public String index(Model model) {
		Customer item = new Customer();
		model.addAttribute("item", item);
		List<Customer> items = dao.findAll();
		model.addAttribute("items", items);
		return "dashboard/apps_customers";
	}
	
	@RequestMapping("/apps_customers/edit/{username}")
	public String edit(Model model, @PathVariable("username") String username) {
		Customer item = dao.findById(username).get();
		model.addAttribute("item", item);
		List<Customer> items = dao.findAll();
		model.addAttribute("items", items);
		return "dashboard/apps_customers";
	}

	@RequestMapping("/apps_customers/create")
	public String create(Customer item) {
		dao.save(item);
		return "redirect:/dashboard/apps_customers";
	}

	@RequestMapping("/apps_customers/update")
	public String update(Customer item) {
		dao.save(item);
		return "redirect:/apps_customers/edit/" + item.getUsername();
	}

	@RequestMapping("/apps_customers/delete/{username}")
	public String create(@PathVariable("username") String username) {
		dao.deleteById(username);
		return "redirect:/dashboard/apps_customers";
	}
	//editprofile
	

	
//	@PostMapping("/dashboard/search")
//	public String searchAndPageDSL(Model model, 
//		@RequestParam("keywords") String kw) {
//		List<Customer> items = dao.findByKeywords(kw);
//		model.addAttribute("item", items);
//		return "/dashboard/apps_customers";
//	}
}
