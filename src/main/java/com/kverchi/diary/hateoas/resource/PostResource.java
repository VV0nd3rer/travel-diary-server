package com.kverchi.diary.hateoas.resource;

import com.kverchi.diary.model.entity.Post;
import org.springframework.hateoas.ResourceSupport;

import java.time.ZonedDateTime;

/**
 * Created by Liudmyla Melnychuk on 4.6.2019.
 */
public class PostResource extends ResourceSupport {
    private final int postId;
    private final String title;
    private final String description;
    private final ZonedDateTime createdAt;
    public PostResource(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.createdAt = post.getCreatedAt();
    }
    public int getPostId() {
        return postId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
