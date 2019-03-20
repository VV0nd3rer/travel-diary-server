package com.kverchi.diary.controller;

import com.kverchi.diary.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class MainController {

	@GetMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		return "redirect:/";
	}

	@GetMapping("/test")
	@ResponseBody
	public User test() {
		User model = new User();
		model.setUsername(UUID.randomUUID().toString());
		model.setPassword("Hello World");
		return model;
	}

	@RequestMapping("/transaction-error")
	public ModelAndView showTransactionErrorPage() {
		ModelAndView mv = new ModelAndView("error/transaction-error");
		return mv;
	}
	@RequestMapping("/denied")
	public ModelAndView showDeniedPage() {
		ModelAndView mv = new ModelAndView("error/denied-error");
		mv.addObject("result", "Page denied");
		return mv;
	}
	/*@RequestMapping("/")
	public ModelAndView showMainPage() {
		ModelAndView mv = new ModelAndView("redirect:/posts");
		return mv;
	}*/
}
