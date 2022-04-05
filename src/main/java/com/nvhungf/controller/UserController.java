package com.nvhungf.controller;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nvhungf.dao.CategoryDao;
import com.nvhungf.dao.CustomerDao;
import com.nvhungf.dao.ProductDao;
import com.nvhungf.entity.Category;
import com.nvhungf.entity.Customer;
import com.nvhungf.entity.Product;
import com.nvhungf.service.SessionService;

@Controller
public class UserController {
	@Autowired
	CustomerDao dao;
	@Autowired
	ProductDao daop;
	@Autowired
	CategoryDao daocate;
	
	
	@Autowired
	SessionService session;
	
	
	@RequestMapping("/user/login")
	public String login() {
		return "/user/login";
	}
	@PostMapping("/user/login")
	public String login(Model model,
	@RequestParam("username") String username,
	@RequestParam("password") String password,
	HttpSession sess2) {
	try {
	Customer customer = dao.getOne(username);
	if(!customer.getPassword().equals(password)) {
	model.addAttribute("message", "Invalid password");
	} else {
	String uri = session.get("security-uri");
	if(uri != null) {
	return "redirect:" + uri;
	} else {
	sess2.setAttribute("username", username);
	System.out.println(username);
	model.addAttribute("message", "Login succeed");
	Product item = new Product();
	model.addAttribute("item", item);
	Category itemcate = new Category();
	model.addAttribute("itemcate", itemcate);
	List<Product> items = daop.findAll();
	model.addAttribute("items", items);
	List<Product> itemf = daop.findFproduct();
	model.addAttribute("itemf", itemf);
	List<Product> itemsale = daop.findSale();
	model.addAttribute("itemsale", itemsale);
	List<Category> itemscate = daocate.findAll();
	model.addAttribute("itemscate", itemscate);
	return "home/index";
	}
	}
	} catch (Exception e) {
	model.addAttribute("message", "Invalid username");
	}
	return "/user/login";
	}
	
	@RequestMapping("/user/signup")
	public String signup(Model model) {
		Customer item = new Customer();
		model.addAttribute("item", item);
		return "/user/signup";
	}
	
	
	@RequestMapping("/user/reg")
	public String create(ModelMap model, Customer item,
			@RequestParam("email") String email,
			@RequestParam("username") String username,
			@RequestParam("password") String password ){
			String subject = "[Harlex] Successful account registration !!!";
			String body = "Your account information :  "
						+ "Username : "  + username 
						+ "Password : "  + password ;
		try{
			// Tạo mail
			MimeMessage mail =mailer.createMimeMessage();
			// Sử dụng lớp trợ giúp
			//MimeMessageHelper helper = new MimeMessageHelper(mail);
                        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(body, true);

			// Gửi mail
			mailer.send(mail);			
			model.addAttribute("message", "Gửi email thành công !");
		}
		catch(Exception ex){
			model.addAttribute("message", "Gửi email thất bại !");
		}
		dao.save(item);
		return "redirect:/user/login";
	}
	
	
	//==========Mail==========//
	@Autowired
	JavaMailSender mailer;
	
	
	@RequestMapping("/user/forgot")
	public String index2() {
		return "/user/forgot";
	}
	//==========ATTACH FILE==========//
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("user/send.html")
	public String send2(ModelMap model, 
			@RequestParam("to") String email)  {
			String items = dao.findPass(email);
			String subject = "[Harlex] Recover your password !!!";
			String body = "Your Password is : " +items;
		try{
			// Tạo mail
			MimeMessage mail =mailer.createMimeMessage();
			// Sử dụng lớp trợ giúp
			//MimeMessageHelper helper = new MimeMessageHelper(mail);
                        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(body, true);

			// Gửi mail
			mailer.send(mail);			
			model.addAttribute("message", "Email sent successfully!! Please check your email");
		}
		catch(Exception ex){
			model.addAttribute("message", "Send email fail!");
		}
		return "/user/forgot";
	}
	
	@RequestMapping("/customer/edit-profile")
	public String create(ModelMap model){
		Customer item = new Customer();
		model.addAttribute("item", item);
		return "/user/edit";
	}
	
	@RequestMapping("/user/edit-profile")
	public String updateProfile(ModelMap model, Customer item) {
		dao.save(item);
		return "redirect:/home/index";
	}
	

}
