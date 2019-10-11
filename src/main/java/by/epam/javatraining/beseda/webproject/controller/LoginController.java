package by.epam.javatraining.beseda.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;

@Controller
@RequestMapping("/")
public class LoginController {

//	@Autowired
//	public UserInterface userDAO;
	
	
	@GetMapping({"/"})
	public String hello() {
		return "hello";
	}
	
//	@GetMapping({"/index","/login"})
//	public String login() {
//		return "login";
//	}
//
//	@GetMapping("/register")
//	public String customerRegister() {
//		return "customer";
//	}
//	
////	Исправить!!!!!
//	@GetMapping("/css/styles.css")
//	public String cssStyles() {
//		return "styles.css";
//	}
}
