package com.kverchi.diary.controller;

import com.kverchi.diary.hateoas.assembler.UsersListResourceAssembler;
import com.kverchi.diary.hateoas.resource.SightsListResource;
import com.kverchi.diary.hateoas.resource.UsersListResource;
import com.kverchi.diary.service.user.impl.ServiceResponse;
import com.kverchi.diary.model.form.RegistrationForm;
import com.kverchi.diary.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.kverchi.diary.model.entity.User;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

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
    @GetMapping("/all")
    public ResponseEntity<PagedResources<UsersListResource>> getAllUsers(
            @RequestParam(name = "text", required = false) String text) {
        Page<User> userList;
        if(text != null) {
            userList = userService.getAllUsers(text);
        } else {
            userList = userService.getAllUsers();
        }
        List<UsersListResource> userResources = new UsersListResourceAssembler().toResources(userList);
        PagedResources.PageMetadata pageMetadata =
                new PagedResources.PageMetadata(
                        userList.getSize(), userList.getNumber(),
                        userList.getTotalElements(), userList.getTotalPages()
                );
        PagedResources<UsersListResource> pagedResources =
                new PagedResources<UsersListResource>(userResources, pageMetadata);
        pagedResources.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(UserController.class).getAllUsers(text)
        ).withSelfRel());
        return new ResponseEntity<PagedResources<UsersListResource>>(pagedResources, HttpStatus.OK);

    }

}
