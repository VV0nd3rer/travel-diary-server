package com.kverchi.diary.hateoas.resource;

import com.kverchi.diary.model.entity.Comment;
import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.model.entity.User;
import org.springframework.hateoas.ResourceSupport;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by Liudmyla Melnychuk on 12.7.2019.
 */
public class SinglePostResource extends ResourceSupport {
    private final int postId;
    private final String title;
    private final String description;
    private final String previewImageUrl;
    private final String text;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
    private final User author;
    private final Sight sight;

    public SinglePostResource(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.previewImageUrl = post.getPreviewImageUrl();
        this.text = post.getText();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.author = post.getAuthor();
        this.sight = post.getSight();
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

    public String getText() {
        return text;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User getAuthor() {
        return author;
    }

    public Sight getSight() {
        return sight;
    }

    public String getPreviewImageUrl() {
        return previewImageUrl;
    }
}
