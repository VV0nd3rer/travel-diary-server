package com.kverchi.diary.controller;

import com.kverchi.diary.hateoas.assembler.PostResourceAssembler;
import com.kverchi.diary.hateoas.resource.PostResource;
import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.service.PostService;
import com.kverchi.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Kverchi on 20.6.2018.
 */
@RestController
@RequestMapping("/posts")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public Post getTestPost() {
        return postService.getAllPosts().get(0);
    }

    @GetMapping("/all")
    public ResponseEntity<Resources<PostResource>> getAllPosts() {
        List<Post> postList = postService.getAllPosts();
        if(!postList.isEmpty()) {
            List<PostResource> postResources = new PostResourceAssembler().toResources(postList);
            Resources<PostResource> allResources = new Resources<PostResource>(postResources);
            allResources.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(PostController.class).getAllPosts())
                    .withRel("all")
            );
            return new ResponseEntity<Resources<PostResource>>(allResources, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<Resources<PostResource>> getPosts(
            @RequestParam(required = false) Map<String, String> reqParams) {

        Page<Post> postList = postService.getPosts(reqParams);
        if(!postList.isEmpty()) {
            List<PostResource> postResources = new PostResourceAssembler().toResources(postList);
            Resources<PostResource> allResources = new Resources<PostResource>(postResources);
            allResources.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(PostController.class).getAllPosts())
                            .withRel("all")
            );
            return new ResponseEntity<Resources<PostResource>>(allResources, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResource> getPostById(@PathVariable("id") int id) {
        Optional<Post> postOptional = postService.getPostById(id);
        if(postOptional.isPresent()) {
            PostResource postResource = new PostResourceAssembler().toResource(postOptional.get());

            return new ResponseEntity<PostResource>(postResource, HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") int id) {
        try {
            postService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
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

}
