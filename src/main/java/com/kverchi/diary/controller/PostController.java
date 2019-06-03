package com.kverchi.diary.controller;

import com.kverchi.diary.model.PostSearchRequest;
import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.service.PostService;
import com.kverchi.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kverchi on 20.6.2018.
 */
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public Post getTestPost() {
        return postService.getAllPosts().get(0);
    }



    @GetMapping("/page/{currentPage}")
    @ResponseBody
    public List<Post> getPostsPage(@PathVariable("currentPage") int currentPage) {
        Page<Post> paginatedPosts = postService.getAllPosts(currentPage);
        List<Post> posts = paginatedPosts.getContent();
        return posts;
    }
    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public Page<Post> getPage(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        Page<Post> page = postService.getAllPosts(currentPage, pageSize);
        return page;
    }
    @PostMapping("/search")
    @ResponseBody
    public Page<Post> search(@RequestBody PostSearchRequest postSearchRequest) {
        Page<Post> page = postService.searchPosts(postSearchRequest);
        return page;
    }



}
