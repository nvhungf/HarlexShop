package com.nvhungf.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nvhungf.dao.AccountDao;
import com.nvhungf.entity.Account;
import com.nvhungf.entity.Category;
import com.nvhungf.entity.Customer;
import com.nvhungf.entity.Product;
import com.nvhungf.service.SessionService;

@Controller
public class DashboardController {
	
	

	@Autowired
	SessionService session;
	@Autowired
	AccountDao dao;
	@RequestMapping("/admin/dashboard")
	public String index(Model model) {
		return "dashboard/dashboard";
	}
	
	
	@RequestMapping("/dashboard/login")
	public String login() {
		return "/dashboard/login";
	}
	@PostMapping("/dashboard/login")
	public String login(Model model,
	@RequestParam("username") String username,
	@RequestParam("password") String password,
	HttpSession sess) {
	try {
	Account account = dao.getOne(username);
	if(!account.getPassword().equals(password)) {
	model.addAttribute("message", "Invalid password");
	} else {
	String uri = session.get("security-uri");
	if(uri != null) {
	return "redirect:" + uri;
	} else {
	sess.setAttribute("username", username);
	model.addAttribute("message", "Login succeed");
	return "dashboard/dashboard";
	}
	}
	} catch (Exception e) {
	model.addAttribute("message", "Invalid username");
	}
	return "/dashboard/login";
	}
}
