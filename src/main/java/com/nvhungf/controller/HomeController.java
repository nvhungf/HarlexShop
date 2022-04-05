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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nvhungf.dao.CategoryDao;
import com.nvhungf.dao.ProductDao;
import com.nvhungf.entity.Category;
import com.nvhungf.entity.Product;
import com.nvhungf.service.SessionService;

@Controller
public class HomeController {
	@Autowired
	ProductDao dao;
	@Autowired
	CategoryDao daocate;
	
	@Autowired
	SessionService session;
	
	@RequestMapping("/home/index")
	public String index(Model model) {
		Product item = new Product();
		model.addAttribute("item", item);
		Category itemcate = new Category();
		model.addAttribute("itemcate", itemcate);
		List<Product> items = dao.findAll();
		model.addAttribute("items", items);
		List<Product> itemf = dao.findFproduct();
		model.addAttribute("itemf", itemf);
		List<Product> itemsale = dao.findSale();
		model.addAttribute("itemsale", itemsale);
		List<Category> itemscate = daocate.findAll();
		model.addAttribute("itemscate", itemscate);
		return "home/index";
	}
	
	@RequestMapping("/home/details/{id}")
	public String details(Model model, @PathVariable("id") String id) {
		List<Product> items = dao.findDetails(id);
		model.addAttribute("items", items);
		List<Product> reitems = dao.findByCate(id);
		model.addAttribute("reitems", reitems);
		List<Category> itemscate = daocate.findAll();
		model.addAttribute("itemscate", itemscate);
		return "/home/details";
	}
	
	
	@RequestMapping("/home/product/{id}")
	public String product(Model model, @PathVariable("id") String id) {
		List<Product> items = dao.findByCate(id);
		model.addAttribute("items", items);
		List<Category> itemscate = daocate.findAll();
		model.addAttribute("itemscate", itemscate);
		return "/home/productCate";
	}
	
	@RequestMapping("/home/allproduct")
	public String allProduct(Model model,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Page<Product> page = dao.findAll(pageable);
		model.addAttribute("page", page);
		List<Category> itemscate = daocate.findAll();
		model.addAttribute("itemscate", itemscate);
		return "/home/productAll";
	}
	
	
	
	
	@RequestMapping("/product/search-price")
	public String price(Model model,
			@RequestParam("min")double min,
			@RequestParam("max")double max) {	
			List<Product> items = dao.findByPrice(min, max);
			model.addAttribute("items", items);
			List<Category> itemscate = daocate.findAll();
			model.addAttribute("itemscate", itemscate);		
			return "/home/productCate";	
	}
}
