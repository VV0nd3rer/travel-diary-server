package com.kverchi.diary.controller;

import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
