package com.kverchi.diary.service.post.impl;

import java.util.List;
import java.util.Optional;

import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.repository.PostRepository;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.kverchi.diary.service.post.PostService;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

import static com.kverchi.diary.repository.predicates.PostPredicates.*;

@Service
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);


    public static final String SORT_BY_DATE = "updatedAt";

    @Autowired
    PostRepository postRepository;

    @Override
    public Page<Post> getAllPosts() {
        Pageable pageable = Pageable.unpaged();
        Page<Post> page =  postRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Post> getPosts(Predicate predicate, int page, int size, String sorting) {
        Sort sort = Sort.unsorted();
        PostSortingCriteria sortingType = PostSortingCriteria.valueOf(sorting.toUpperCase());

        switch (sortingType) {
            case NEWEST:
                logger.info("sorting by newest");
                sort = Sort.by(SORT_BY_DATE).descending();
                break;
            case OLDEST:
                logger.info("sorting by oldest");
                sort = Sort.by(SORT_BY_DATE);
                break;
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> posts = postRepository.findAll(predicate, pageable);
        return posts;
    }

    @Override
    public Optional<Post> getPostById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }


}
