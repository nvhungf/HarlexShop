package com.nvhungf.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nvhungf.bean.CartItem;
import com.nvhungf.dao.CategoryDao;
import com.nvhungf.dao.ProductDao;
import com.nvhungf.entity.Category;
import com.nvhungf.entity.Product;
import com.nvhungf.service.ParamService;
import com.nvhungf.service.ShoppingCartService;


@RequestMapping("cart")
@Controller
public class ShoppingCartController {

	
	@Autowired
	ProductDao dao;
	@Autowired
	CategoryDao daocate;
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@GetMapping("view")
	public String list(Model model) {
		Collection<CartItem> cartitems = shoppingCartService.getCartItems();
		System.out.println("item :" + cartitems);
		List<Category> itemscate = daocate.findAll();
		model.addAttribute("itemscate", itemscate);
		model.addAttribute("cartItems", cartitems);
		model.addAttribute("total", shoppingCartService.getAmount());
		model.addAttribute("NoOfItems", shoppingCartService.getCount());
		return "/cart/shoppingCart";
	}
	
	@GetMapping("add/{id}")
	public String add(@PathVariable("id") String id) {
		
		Optional<Product> product = dao.findById(id);
		
		if(product.isPresent()) {
			Product entity = product.get();
			CartItem item = new CartItem();
			BeanUtils.copyProperties(entity, item);
			item.setQuantity(1);
			shoppingCartService.add(item);
			System.out.println(item);
		}
		return "redirect:/cart/view";
	}
	
	@GetMapping("remove/{id}")
	public String remove(@PathVariable("id") String id) {
		System.out.println(id);
		shoppingCartService.remove(id);
		
		return "redirect:/cart/view";
	}
	
	@GetMapping("clear")
	public String clear() {
		shoppingCartService.clear();
		return "redirect:/cart/view";
	}
	
}
