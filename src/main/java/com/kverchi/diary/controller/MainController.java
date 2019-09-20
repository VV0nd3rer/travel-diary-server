package com.kverchi.diary.controller;

import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.service.security.SecurityService;
import com.kverchi.diary.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	UserService userService;
	@Autowired
	SecurityService securityService;

	@GetMapping("/echo")
	@ResponseBody
	public String echo() {
		return "Hello from main controller.";
	}

	@GetMapping("/invalid-session")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void invalidSession() {
		logger.info("Invalid session");
	}
	@GetMapping("/expired-session")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void expiredSession() {
		logger.info("Expired session");
	}


}
