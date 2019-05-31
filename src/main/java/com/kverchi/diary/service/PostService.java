package com.kverchi.diary.service;

import java.util.List;

import com.kverchi.diary.model.PostSearchRequest;
import com.kverchi.diary.model.ServiceResponse;
import com.kverchi.diary.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
	List<Post> getAllPosts();
	Page<Post> getAllPosts(int currentPage);
	Page<Post> getAllPosts(int currentPage, int pageSize);

	Page<Post> searchPosts(PostSearchRequest postSearchRequest);

	Post getPostById(int postId);

	ServiceResponse addPost(Post post);
	ServiceResponse updatePost(Post post);
	void deletePost(int post_id);

	List<Post> getSightPosts(int sightId);
	Page<Post> getSightPosts(Pageable pageable);

}
