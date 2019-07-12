package com.kverchi.diary.controller;

import com.kverchi.diary.hateoas.assembler.PostsListResourceAssembler;
import com.kverchi.diary.hateoas.assembler.SinglePostResourceAssembler;
import com.kverchi.diary.hateoas.resource.PostsListResource;
import com.kverchi.diary.hateoas.resource.SinglePostResource;
import com.kverchi.diary.model.PaginationResponse;
import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.service.post.PostService;
import com.kverchi.diary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by Kverchi on 20.6.2018.
 */
@RestController
@RequestMapping("/posts")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private static final String DEFAULT_PAGE_SIZE_VALUE = "5";
    private static final String DEFAULT_CURRENT_PAGE_VALUE = "0";
    private static final String DEFAULT_SORTING_VALUE = "unsorted";

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public Post getTestPost() {
        return postService.getAllPosts().get(0);
    }

    @GetMapping("/all")
    public ResponseEntity<Resources<PostsListResource>> getAllPosts() {
        List<Post> postList = postService.getAllPosts();
        if (!postList.isEmpty()) {
            List<PostsListResource> postResources = new PostsListResourceAssembler().toResources(postList);
            Resources<PostsListResource> allResources = new Resources<PostsListResource>(postResources);
            allResources.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(PostController.class).getAllPosts())
                            .withRel("all")
            );
            return new ResponseEntity<Resources<PostsListResource>>(allResources, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<PaginationResponse> getPosts(
            @QuerydslPredicate(root = Post.class) Predicate predicate,
            @RequestParam(name = "page", defaultValue = DEFAULT_CURRENT_PAGE_VALUE) int page,
            @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE_VALUE) int size,
            @RequestParam(name = "sorting", defaultValue = DEFAULT_SORTING_VALUE) String sorting) {

        Page<Post> postList = postService.getPosts(predicate, page, size, sorting);

        if(!postList.isEmpty()) {
            PaginationResponse paginationResponse = new PaginationResponse(page, size,
                    postList.getTotalPages(), postList.getTotalElements());
            List<PostsListResource> postResources = new PostsListResourceAssembler().toResources(postList);
            Resources<PostsListResource> allResources = new Resources<PostsListResource>(postResources);
            allResources.add(
                    ControllerLinkBuilder.linkTo(
                            ControllerLinkBuilder.methodOn(PostController.class)
                                    .getPosts(predicate,
                                            page,
                                            size,
                                            sorting)).withSelfRel()
            );
            paginationResponse.setResources(allResources);
            return new ResponseEntity<PaginationResponse>(paginationResponse, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SinglePostResource> getPostById(@PathVariable("id") int id) {
        Optional<Post> postOptional = postService.getPostById(id);
        if(postOptional.isPresent()) {
            SinglePostResource postResource = new SinglePostResourceAssembler().toResource(postOptional.get());

            return new ResponseEntity<SinglePostResource>(postResource, HttpStatus.OK);
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
