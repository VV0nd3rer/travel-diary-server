package com.kverchi.diary.controller;

import com.kverchi.diary.model.entity.Book;
import com.kverchi.diary.service.book.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Liudmyla Melnychuk on 12.4.2019.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @GetMapping("/test")
    public String test() {
        return "Hello from books!";
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        if(!bookList.isEmpty()) {
            return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        Optional<Book> bookOptional = bookService.getBookById(id);
        if(bookOptional.isPresent()) {
            return new ResponseEntity<Book>(bookOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
}
