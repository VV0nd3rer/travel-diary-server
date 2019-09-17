package com.kverchi.diary.service.post.impl;

import java.util.List;
import java.util.Optional;

import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.repository.PostRepository;
import com.kverchi.diary.repository.SightRepository;
import com.kverchi.diary.service.security.SecurityService;
import com.kverchi.diary.service.sight.SightService;
import com.kverchi.diary.service.user.UserService;
import com.kverchi.diary.service.user.impl.MsgServiceResponse;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kverchi.diary.repository.predicates.PostPredicates.*;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);


    public static final String SORT_BY_DATE = "updatedAt";

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    SightService sightService;

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
    public Post updatePost(Post post) throws BadCredentialsException {
        User user = userService.getUserFromSession();
        if(user.getUserId() != post.getAuthor().getUserId()) {
            throw new BadCredentialsException(MsgServiceResponse.FORBIDDEN_ACTION.toString());
        }

        Sight updatedSightFromRepo = sightService.getSightByLabel(post.getSight().getLabel());
        if(updatedSightFromRepo == null) {
            Sight newSight = new Sight();
            newSight.setLabel(post.getSight().getLabel());
            newSight.setDescription(post.getSight().getDescription());
            newSight.setLatitude(post.getSight().getLatitude());
            newSight.setLongitude(post.getSight().getLongitude());
            updatedSightFromRepo = sightService.saveSight(newSight);
        }
        post.setSight(updatedSightFromRepo);
        return postRepository.save(post);
    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }


}
