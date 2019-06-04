package com.kverchi.diary.service;

import com.kverchi.diary.model.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * Created by Liudmyla Melnychuk on 3.6.2019.
 */
public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(int id);
    Book saveBook(Book book);
}
