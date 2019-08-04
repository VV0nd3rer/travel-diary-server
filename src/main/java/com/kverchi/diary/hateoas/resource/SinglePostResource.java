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
    private final String text;
    private final ZonedDateTime updatedAt;
    private final User author;
    private final Sight sight;
    private final Set<Comment> comments;

    public SinglePostResource(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.updatedAt = post.getUpdatedAt();
        this.author = post.getAuthor();
        this.sight = post.getCountriesSight();
        this.comments = post.getComments();
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
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

    public Set<Comment> getComments() {
        return comments;
    }
}
