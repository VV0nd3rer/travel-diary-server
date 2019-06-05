package com.kverchi.diary.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.kverchi.diary.model.PostSearchRequest;
import com.kverchi.diary.model.entity.Post;
import com.kverchi.diary.model.ServiceResponse;
import com.kverchi.diary.model.enums.PostSearchingCriteria;
import com.kverchi.diary.repository.PostRepository;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kverchi.diary.service.PostService;

import static com.kverchi.diary.repository.predicates.PostPredicates.*;

@Service
public class PostServiceImpl implements PostService {
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Page<Post> getAllPosts(int currentPage) {
		Pageable pageable = PageRequest.of(currentPage, 5);
		Page<Post> page =  postRepository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Post> getAllPosts(int currentPage, int pageSize) {
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		Page<Post> page = postRepository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Post> searchPosts(PostSearchRequest postSearchRequest) {
		
		BooleanBuilder builder = new BooleanBuilder();
		Map<PostSearchingCriteria, Object> searchAttributes = postSearchRequest.getSearchAttributes();
        for (Map.Entry<PostSearchingCriteria, Object> entry : searchAttributes.entrySet()) {
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
		Pageable pageable = PageRequest.of(postSearchRequest.getCurrentPage(), postSearchRequest.getPageSize());
		Page<Post> page = postRepository.findAll(builder, pageable);
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
