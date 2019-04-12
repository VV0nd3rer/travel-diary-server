package com.kverchi.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Liudmyla Melnychuk on 12.4.2019.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Hello from books!";
    }
}
