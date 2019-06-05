package com.kverchi.diary.service;

import java.util.List;
import java.util.Optional;

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

	Optional<Post> getPostById(int id);
	Post savePost(Post post);
	Post updatePost(Post post);
	void deleteById(int id);

}
