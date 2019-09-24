package com.kverchi.diary.controller;

import com.kverchi.diary.hateoas.assembler.UsersListResourceAssembler;
import com.kverchi.diary.hateoas.resource.SightsListResource;
import com.kverchi.diary.hateoas.resource.UsersListResource;
import com.kverchi.diary.service.user.impl.ServiceResponse;
import com.kverchi.diary.model.form.RegistrationForm;
import com.kverchi.diary.service.user.UserService;
import com.kverchi.diary.service.user.impl.UserSearchingCriteria;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import java.util.Map;

/**
 * Created by Liudmyla Melnychuk on 12.12.2018.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String DEFAULT_PAGE_SIZE_VALUE = "5";
    private static final String DEFAULT_CURRENT_PAGE_VALUE = "0";
    private static final String DEFAULT_SORTING_VALUE = "unsorted";
    @Value( "${client.url}" )
    private String clientUrl;

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
        boolean isUserActivated = userService.activateAccount(securityToken);
        if(isUserActivated) {
            return new RedirectView(clientUrl + "/login");
        }
        else {
            return new RedirectView(clientUrl + "/not-found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResources<UsersListResource>> getAllUsers(
            @RequestParam(name="searchLikeAttr", required = false) String searchLikeAttr) {
        Page<User> usersPage;
        if (searchLikeAttr != null) {
            usersPage = userService.getAllUsers(searchLikeAttr);
        } else {
            usersPage = userService.getAllUsers();
        }
        List<UsersListResource> userResources = new UsersListResourceAssembler().toResources(usersPage);
        PagedResources.PageMetadata pageMetadata =
                new PagedResources.PageMetadata(
                        usersPage.getSize(), usersPage.getNumber(),
                        usersPage.getTotalElements(), usersPage.getTotalPages()
                );
        PagedResources<UsersListResource> pagedResources =
                new PagedResources<UsersListResource>(userResources, pageMetadata);
        pagedResources.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(UserController.class).getAllUsers(searchLikeAttr)
        ).withSelfRel());
        return new ResponseEntity<PagedResources<UsersListResource>>(pagedResources, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedResources<UsersListResource>> getUsers(
            @QuerydslPredicate(root = User.class) Predicate predicate,
            @RequestParam(name = "searchLikeAttr", required = false) String searchLikeAttr,
            @RequestParam(name = "page", defaultValue = DEFAULT_CURRENT_PAGE_VALUE) int page,
            @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE_VALUE) int size,
            @RequestParam(name = "sorting", defaultValue = DEFAULT_SORTING_VALUE) String sorting) {
        Page<User> usersPage;
        if(searchLikeAttr != null) {
            usersPage = userService.getUsers(predicate, searchLikeAttr,
                    page, size, sorting);
        }
        else {
            usersPage = userService.getUsers(predicate, page, size, sorting);
        }
        List<UsersListResource> usersListResources = new UsersListResourceAssembler().toResources(usersPage);
        PagedResources.PageMetadata pageMetadata = new PagedResources.PageMetadata(
                usersPage.getSize(), usersPage.getNumber(),
                usersPage.getTotalElements(), usersPage.getTotalPages()
        );
        PagedResources<UsersListResource> pagedResources =
                new PagedResources<UsersListResource>(usersListResources, pageMetadata);
        pagedResources.add(
                ControllerLinkBuilder.linkTo(
                        ControllerLinkBuilder.methodOn(UserController.class)
                        .getUsers(predicate, searchLikeAttr, page, size, sorting))
                .withSelfRel());
        return new ResponseEntity<PagedResources<UsersListResource>>(pagedResources, HttpStatus.OK);
    }

}
