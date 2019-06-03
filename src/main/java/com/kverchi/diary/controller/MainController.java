package com.kverchi.diary.controller;

import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class MainController {


	@Autowired
	UserService userService;

	@GetMapping("/test")
	@ResponseBody
	public User test() {
		return userService.getUserFromSession();
	}

	@GetMapping("/echo")
	@ResponseBody
	public String echo() {
		return "Hello from main controller.";
	}

}
