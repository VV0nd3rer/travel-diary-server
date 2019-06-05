package com.kverchi.diary.hateoas.resource;

import com.kverchi.diary.model.entity.Post;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Liudmyla Melnychuk on 4.6.2019.
 */
public class PostResource extends ResourceSupport {
    private final int postId;
    private final String title;
    private final String description;
    public PostResource(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.description = post.getDescription();
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
}
