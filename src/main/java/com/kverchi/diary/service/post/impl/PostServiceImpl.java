package com.kverchi.diary.service.post.impl;

import java.util.Optional;

import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.repository.PostRepository;
import com.kverchi.diary.service.sight.SightService;
import com.kverchi.diary.service.user.impl.MsgServiceResponse;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);


    public static final String SORT_BY_DATE = "updatedAt";

    @Autowired
    PostRepository postRepository;

    @Autowired
    SightService sightService;

    @Override
    public Page<Post> getAllPosts() {
        Pageable pageable = Pageable.unpaged();
        Page<Post> page = postRepository.findAll(pageable);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = new User();
        if (authentication.getPrincipal() instanceof User) {
            user  = (User) authentication.getPrincipal();
        }
        post.setAuthor(user);
        Sight sightFromRepo = prepareSight(post);
        post.setSight(sightFromRepo);
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) throws BadCredentialsException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            if (user.getUserId() != post.getAuthor().getUserId()) {
                throw new BadCredentialsException(MsgServiceResponse.FORBIDDEN_ACTION.toString());
            }
        }
        Sight sightFromRepo = prepareSight(post);
        post.setSight(sightFromRepo);
        return postRepository.save(post);
    }
    private Sight prepareSight(Post post) {
        Sight sightFromRepo = sightService.getSightByLabel(post.getSight().getLabel());
        if (sightFromRepo == null) {
            sightFromRepo = createSightForPost(post);
        }
        sightFromRepo.setDescription(post.getSight().getDescription());
        return sightFromRepo;
    }
    private Sight createSightForPost(Post post) {
        Sight newSight = new Sight();
        newSight.setLabel(post.getSight().getLabel());
        newSight.setDescription(post.getSight().getDescription());
        newSight.setLatitude(post.getSight().getLatitude());
        newSight.setLongitude(post.getSight().getLongitude());
        return sightService.saveSight(newSight);
    }
    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }


}
