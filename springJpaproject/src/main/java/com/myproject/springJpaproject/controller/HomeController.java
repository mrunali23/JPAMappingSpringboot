package com.myproject.springJpaproject.controller;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

	 @GetMapping("/")
	    public String index() {
	        return "index";  // Display login page or home page if logged in
	    }

	    @GetMapping("/home")
	    public String home(){//@AuthenticationPrincipal OAuth2User principal, Model model) {
	       // model.addAttribute("name", principal.getAttribute("name"));
	        return "home";  // Show the home page after successful login
	    }
	
}
