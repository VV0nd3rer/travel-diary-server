package com.kverchi.diary.model.entity;

/**
 * Created by Liudmyla Melnychuk on 10.7.2019.
 */

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int commentId;
    @Column(name="created_at")
    private ZonedDateTime createdAt;
    @Column(name="text")
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User author;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
