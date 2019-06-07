package com.kverchi.diary.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.model.enums.PostRequestParams;
import com.kverchi.diary.model.enums.PostSortingCriteria;
import com.kverchi.diary.repository.PostRepository;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.kverchi.diary.service.PostService;

import static com.kverchi.diary.repository.predicates.PostPredicates.*;

@Service
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    public static final int DEFAULT_PAGE_SIZE = 5;
    public static final int DEFAULT_CURRENT_PAGE = 0;
    public static final String SORT_BY_DATE = "updatedAt";

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> getAllPosts(int currentPage) {
        Pageable pageable = PageRequest.of(currentPage, 5);
        Page<Post> page = postRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Post> getAllPosts(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Post> page = postRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Post> getPosts(Map<String, String> reqParams) {
        BooleanBuilder builder = new BooleanBuilder();

        int currentPage = DEFAULT_CURRENT_PAGE;
        int pageSize = DEFAULT_PAGE_SIZE;

        Sort sort = Sort.unsorted();
        for (Map.Entry<String, String> entry : reqParams.entrySet()) {

            PostRequestParams criteria = PostRequestParams.valueOf(entry.getKey().toUpperCase());

            switch (criteria) {
                case AUTHOR:
                    logger.info(criteria.toString());
                    builder.and(searchByAuthorId(Integer.parseInt(entry.getValue())));
                    break;
                case SIGHT:
                    logger.info(criteria.toString());
                    builder.and(searchBySightId(Integer.parseInt(entry.getValue())));
                    break;
                case SORTING:
                    PostSortingCriteria sorting = PostSortingCriteria.valueOf(entry.getValue().toUpperCase());
                    switch (sorting) {
                        case VISITED:
                            logger.info("sorting by visited");
                            break;
                        case WISHED:
                            logger.info("sorting by wished");
                            break;
                        case NEWEST:
                            logger.info("sorting by newest");
                            sort = Sort.by(SORT_BY_DATE).descending();
                            break;
                        case OLDEST:
                            logger.info("sorting by oldest");
                            sort = Sort.by(SORT_BY_DATE);
                            break;
                    }
                    break;
                case CURRENT_PAGE:
                    currentPage = Integer.parseInt(entry.getValue());
                    break;
                case PAGE_SIZE:
                    pageSize = Integer.parseInt(entry.getValue());
                    break;
            }
        }
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
        if(builder.hasValue()) {
            Page<Post> page = postRepository.findAll(builder, pageable);
            return page;
        }
        Page<Post> page = postRepository.findAll(pageable);
        return page;
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
