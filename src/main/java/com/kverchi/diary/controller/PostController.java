package com.kverchi.diary.controller;

import com.kverchi.diary.model.PostSearchRequest;
import com.kverchi.diary.model.entity.CountriesSight;
import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.service.CountriesSightService;
import com.kverchi.diary.service.PostService;
import com.kverchi.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Kverchi on 20.6.2018.
 */
@RestController
@RequestMapping("posts")
@SessionAttributes("currentSight")
public class PostController {
    private final static String POSTS = "posts";
    private final static String SINGLE_POST = "single-post";

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    CountriesSightService countriesSightService;

    @ModelAttribute("currentSight")
    public CountriesSight getCurrentSight () {
        return new CountriesSight();
    }

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "redirect:/";
    }

    @RequestMapping("/main")
    public ModelAndView showMain(
            @RequestParam(value="name", required=false, defaultValue="Guest") String name) {
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("message", "Welcome home!");
        mv.addObject("name", name);
        return mv;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPostsPage(@ModelAttribute("currentSight") CountriesSight currentSight) {
        ModelAndView mv = new ModelAndView(POSTS);
        List<User> users = userService.findAll();
        List<CountriesSight> sights = countriesSightService.findAll();
        currentSight = new CountriesSight();
        mv.addObject("currentSight", currentSight);
        mv.addObject("authors", users);
        mv.addObject("sights", sights);
        return mv;
    }
    @RequestMapping(value = "/paginated-posts/{currentPage}")
    public ModelAndView showPaginatedPosts(@PathVariable("currentPage") int currentPage) {
        ModelAndView mv = new ModelAndView("fragments/content/postContent");
        Page<Post> paginatedPosts = postService.getAllPosts(currentPage);
        List<Post> posts = paginatedPosts.getContent();
        int totalPages = paginatedPosts.getTotalPages();
        mv.addObject("posts", posts);
        mv.addObject("totalPages", totalPages);
        return mv;
    }
    @RequestMapping("/page/{currentPage}")
    @ResponseBody
    public List<Post> getPostsPage(@PathVariable("currentPage") int currentPage) {
        Page<Post> paginatedPosts = postService.getAllPosts(currentPage);
        List<Post> posts = paginatedPosts.getContent();
        return posts;
    }
    @RequestMapping(value="/search-paginated-posts", method = RequestMethod.POST)
    public ModelAndView searchPaginatedPosts(@RequestBody PostSearchRequest postSearchRequest) {
        ModelAndView mv = new ModelAndView("fragments/content/postContent");
        Page<Post>  paginatedPosts = postService.searchPosts(postSearchRequest);
        List<Post> posts = paginatedPosts.getContent();
        int totalPages = paginatedPosts.getTotalPages();
        mv.addObject("posts", posts);
        mv.addObject("totalPages", totalPages);
        return mv;
    }
    @RequestMapping("/{postId}")
    public ModelAndView showSinglePost(@PathVariable("postId") int postId,
                                       @ModelAttribute("currentSight") CountriesSight currentSight) {
        ModelAndView mv = new ModelAndView(SINGLE_POST);

        Post post = postService.getPostById(postId);
        //Set<Comment> comments = post.getPostComments();
        mv.addObject("post", post);
        mv.addObject("currentSight", currentSight);
        //mv.addObject("comments", comments);
        boolean isAuthor = false;
        User currentUser = userService.getUserFromSession();
        if(currentUser != null) {
            isAuthor = currentUser.getUsername().equals(post.getUser().getUsername());
        }
        mv.addObject("isAuthor", isAuthor);

        return mv;
    }
    @RequestMapping("/sight/{sightId}")
    public ModelAndView showSightPosts(@PathVariable("sightId") int sightId) {
        ModelAndView mv = new ModelAndView(POSTS);
        CountriesSight sight = countriesSightService.findBySightId(sightId);
        /*int wishCounter = counterService.getCounterValue(sight_id, Counter.WISHES);
        int visitCounter = counterService.getCounterValue(sight_id, Counter.VISITS);*/
        /*boolean isVisitedValueExists = false;
        boolean isWishedValueExists = false;*/
        /*User currentUser = userService.getUserFromSession();
        if(currentUser != null) {
            isVisitedValueExists = counterService.isCounterValueExists(sight_id, currentUser.getUserId(), Counter.VISITS);
            isWishedValueExists = counterService.isCounterValueExists(sight_id, currentUser.getUserId(), Counter.WISHES);
        }*/

        mv.addObject("authors", userService.findAll());
        mv.addObject("currentSight", sight);/*
        mv.addObject("wishCounter", wishCounter);
        mv.addObject("visitCounter", visitCounter);
        mv.addObject("isWishedValueExists", isWishedValueExists);
        mv.addObject("isVisitedValueExists", isVisitedValueExists);*/
        return mv;
    }

}
