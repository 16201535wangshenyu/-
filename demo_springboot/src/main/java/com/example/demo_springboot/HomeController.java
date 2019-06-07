package com.example.demo_springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class HomeController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		

		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/regist").setViewName("regist");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	private String login(User user){

		return "login";
	}
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	private String regist(User user){

		return "regist";
	}

	@PostMapping("/login")
	public String logincheck(@Validated User user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasFieldErrors()) {
			model.addAttribute(user);
			return "login";
		} else
			return "redirect:home";
	}
	@PostMapping("/regist")
	public String registcheck(@Validated User user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasFieldErrors()) {
			model.addAttribute(user);
			return "regist";
		} else
			return "redirect:login";
	}

}
