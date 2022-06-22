package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	 @GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "user/logout";
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/";
	}

}
