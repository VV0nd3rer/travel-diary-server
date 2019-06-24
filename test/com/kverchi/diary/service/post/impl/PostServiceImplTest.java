package com.kverchi.diary.service.post.impl;

import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.repository.PostRepository;
import com.querydsl.core.types.Predicate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 21.6.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImplTest.class);

    @Autowired
    PostRepository postRepository;

    @Ignore
    @Test
    public void testGetAllPosts() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Post> page = postRepository.findAll(pageable);
        List<Post> posts = page.getContent();
        logger.info("Page content: " + posts.get(0).getTitle());
    }
    @Test
    public void findPostsPreviewTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 15);
        Predicate predicate = null;
        Page<Post> page = postRepository.findPostsPreview(predicate, pageable);
        List<Post> posts = page.getContent();
    }
}