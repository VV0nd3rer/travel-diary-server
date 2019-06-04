package com.kverchi.diary.controller;

import com.kverchi.diary.model.PostSearchRequest;
import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.service.PostService;
import com.kverchi.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Post getTestPost() {
        return postService.getAllPosts().get(0);
    }

    @GetMapping
    public ResponseEntity<Resources<Resource<Post>>> getAllPosts() {
        List<Post> postList = postService.getAllPosts();
        if(!postList.isEmpty()) {
            Resources<Resource<Post>> postResources = Resources.wrap(postList);
            postResources.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(PostController.class).getAllPosts())
                    .withRel("all")
            );
            return new ResponseEntity<Resources<Resource<Post>>>(postResources, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") int id) {
        Optional<Post> postOptional = postService.getPostById(id);
        if(postOptional.isPresent()) {
            return new ResponseEntity<Post>(postOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@RequestBody Post post) {
        return postService.savePost(post);
    }
    @PutMapping
    public Post updatePost(@RequestBody Post post) {
        return postService.savePost(post);
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
