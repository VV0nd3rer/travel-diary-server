package com.kverchi.diary.service.post;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.kverchi.diary.model.entity.Post;
import org.springframework.data.domain.Page;

public interface PostService {
	List<Post> getAllPosts();
	Page<Post> getAllPosts(int currentPage);
	Page<Post> getAllPosts(int currentPage, int pageSize);
	Page<Post> getPosts(Map<String, String> reqParams);
	Optional<Post> getPostById(int id);
	Post savePost(Post post);
	Post updatePost(Post post);
	void deleteById(int id);

}
