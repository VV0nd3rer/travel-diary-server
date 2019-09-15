package com.kverchi.diary.hateoas.resource;

import com.kverchi.diary.model.entity.User;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Created by Liudmyla Melnychuk on 15.9.2019.
 */
@Relation(value = "user", collectionRelation = "users")
public class UsersListResource extends ResourceSupport {
    private final int userId;
    private final String username;

    public UsersListResource(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {

        return userId;
    }
}
