package com.kverchi.diary.controller;

import com.kverchi.diary.model.ServiceResponse;
import com.kverchi.diary.model.form.RegistrationForm;
import com.kverchi.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.kverchi.diary.model.entity.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by Liudmyla Melnychuk on 12.12.2018.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

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
}
