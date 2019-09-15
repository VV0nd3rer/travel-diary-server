package com.kverchi.diary.hateoas.assembler;

import com.kverchi.diary.controller.UserController;
import com.kverchi.diary.hateoas.resource.UsersListResource;
import com.kverchi.diary.model.entity.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Liudmyla Melnychuk on 15.9.2019.
 */
public class UsersListResourceAssembler extends ResourceAssemblerSupport<User, UsersListResource> {
    public UsersListResourceAssembler() {
        super(UserController.class, UsersListResource.class);
    }
    @Override
    protected UsersListResource instantiateResource(User user) {
        return new UsersListResource(user);
    }
    @Override
    public UsersListResource toResource(User user) {
        return createResourceWithId(user.getUserId(), user);
    }
}
