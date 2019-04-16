package com.kverchi.diary.controller;

import com.kverchi.diary.model.ServiceResponse;
import com.kverchi.diary.model.form.RegistrationForm;
import com.kverchi.diary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.kverchi.diary.model.entity.User;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by Liudmyla Melnychuk on 12.12.2018.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    HttpServletResponse httpServletResponse;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ServiceResponse processLogin(@RequestBody User requestUser) {
        ServiceResponse response = userService.login(requestUser);
        return response;
    }
    @GetMapping(value = "/logout")
    @ResponseBody
    public ServiceResponse processLogout() {
        ServiceResponse response = userService.logout();
        return response;
    }
    @PostMapping(value = "/register")
    @ResponseBody
    public ServiceResponse processRegistration(@RequestBody RegistrationForm form) {
        ServiceResponse response = userService.register(form);
        return response;
    }
    @GetMapping(value = "/confirm/{securityToken}")
    public RedirectView confirmRegistration(@PathVariable("securityToken") String securityToken) {
        logger.info("Security token from email: " + securityToken);
        return new RedirectView("http://localhost:4200/login");
    }

}
