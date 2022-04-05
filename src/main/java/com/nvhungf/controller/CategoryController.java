package com.nvhungf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nvhungf.dao.CategoryDao;
import com.nvhungf.entity.Category;
import com.nvhungf.entity.Customer;

@Controller
public class CategoryController {
	@Autowired
	CategoryDao dao;
	@RequestMapping("/dashboard/apps_category")
	public String index(Model model) {
		Category item = new Category();
		model.addAttribute("item", item);
		List<Category> items = dao.findAll();
		model.addAttribute("items", items);
		return "/dashboard/apps_category";
	}
	
	@RequestMapping("/apps_category/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		Category item = dao.findById(id).get();
		model.addAttribute("item", item);
		List<Category> items = dao.findAll();
		model.addAttribute("items", items);
		return "dashboard/apps_category";
	}

	@RequestMapping("/apps_category/create")
	public String create(Category item) {
		dao.save(item);
		return "redirect:/dashboard/apps_category";
	}

	@RequestMapping("/apps_category/update")
	public String update(Category item) {
		dao.save(item);
		return "redirect:/apps_category/edit/" + item.getId();
	}

	@RequestMapping("/apps_category/delete/{id}")
	public String create(@PathVariable("id") String id) {
		dao.deleteById(id);
		return "redirect:/dashboard/apps_category";
	}
	
}
