package com.nvhungf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.nvhungf.dao.CategoryDao;
import com.nvhungf.dao.ProductDao;
import com.nvhungf.entity.Category;
import com.nvhungf.entity.Customer;
import com.nvhungf.entity.Product;
import com.nvhungf.entity.Report;
import com.nvhungf.service.SessionService;


@Controller
public class ProductController {
	@Autowired
	ProductDao dao;
	@Autowired
	CategoryDao daocate;
	
	@Autowired
	SessionService session;
	
	@RequestMapping("/dashboard/apps_product")
	public String index(Model model) {
		Product item = new Product();
		model.addAttribute("item", item);
		List<Product> items = dao.findAll();
		model.addAttribute("items", items);
		return "dashboard/apps_product";
	}
	@RequestMapping("/apps_product/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		Product item = dao.findById(id).get();
		model.addAttribute("item", item);
		List<Product> items = dao.findAll();
		model.addAttribute("items", items);
		return "dashboard/apps_product";
	}

	  @RequestMapping("/dashboard/inventory") 
	  public String inventory(Model model) {
	  List<Report> items = dao.getInventoryByCategory();
	  model.addAttribute("items", items); 
	  return "dashboard/inventory"; 
	  }
	  
	  @RequestMapping("/apps_product/create")
		public String create(Product item) {
			dao.save(item);
			return "redirect:/dashboard/apps_product";
		}

		@RequestMapping("/apps_product/update")
		public String update(Product item) {
			dao.save(item);
			return "redirect:/apps_product/edit/" + item.getId();
		}

		@RequestMapping("/apps_product/delete/{id}")
		public String create(@PathVariable("id") String id) {
			dao.deleteById(id);
			return "redirect:/dashboard/apps_product";
		}

	
}
