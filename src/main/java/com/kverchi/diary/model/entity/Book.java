package com.kverchi.diary.model.entity;


import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by Liudmyla Melnychuk on 3.6.2019.
 */
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bookId;

    @Column(name="book_title")
    private String bookTitle;

    private String author;

    @Column(name="book_description")
    private String bookDescription;

    private ZonedDateTime published;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public ZonedDateTime getPublished() {
        return published;
    }

    public void setPublished(ZonedDateTime published) {
        this.published = published;
    }
}
