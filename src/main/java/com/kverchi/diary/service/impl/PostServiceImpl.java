package com.kverchi.diary.service.impl;

import java.util.List;
import java.util.Map;

import com.kverchi.diary.model.PostSearchRequest;
import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.model.ServiceResponse;
import com.kverchi.diary.model.enums.PostSearchCriteria;
import com.kverchi.diary.repository.PostRepository;
import com.querydsl.core.BooleanBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kverchi.diary.service.PostService;

import static com.kverchi.diary.repository.predicates.PostPredicates.*;

@Service
public class PostServiceImpl implements PostService {
	final static Logger logger = LogManager.getLogger(PostServiceImpl.class);

	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Page<Post> getAllPosts(int currentPage) {
		Pageable pageable = createPageableObject(currentPage);
		Page<Post> page =  postRepository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Post> searchPosts(PostSearchRequest postSearchRequest) {
		
		BooleanBuilder builder = new BooleanBuilder();
		Map<PostSearchCriteria, Object> searchAttributes = postSearchRequest.getSearchAttributes();
        for (Map.Entry<PostSearchCriteria, Object> entry : searchAttributes.entrySet()) {
            switch (entry.getKey()) {
                case BY_AUTHOR_ID:
                    builder.and(searchByAuthorId((Integer)entry.getValue()));
                    break;
                case BY_SIGHT_ID:
                    builder.and(searchBySightId((Integer)entry.getValue()));
                    break;
                case BY_TEXT:
                    //TODO search in text, title and description
                    builder.and(searchInPosts((String)entry.getValue()));
                    break;
				case BY_TEXT_IN_TITLE_ONLY:
					builder.and(searchInTitleOnly((String)entry.getValue()));
					break;
            }
        }
		Pageable pageable = createPageableObject(postSearchRequest.getCurrentPage());
		Page<Post> page = postRepository.findAll(builder, pageable);
		return page;
	}

	@Override
	public Post getPostById(int postId) {
		return postRepository.getOne(postId);
	}

	@Override
	public ServiceResponse addPost(Post post) {
		return null;
	}

	@Override
	public ServiceResponse updatePost(Post post) {
		return null;
	}

	@Override
	public void deletePost(int post_id) {

	}

	@Override
	public List<Post> getSightPosts(int sightId) {
		return postRepository.findByCountriesSightSightId(sightId);
	}

	@Override
	public Page<Post> getSightPosts(Pageable pageable) {
		return null;
	}
	private Pageable createPageableObject(int currentPage) {
		return PageRequest.of(currentPage-1, 5);
	}
}
