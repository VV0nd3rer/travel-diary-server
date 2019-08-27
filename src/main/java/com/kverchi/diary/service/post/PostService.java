package com.kverchi.diary.service.post;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.kverchi.diary.model.entity.Post;
import org.springframework.data.domain.Page;

import com.querydsl.core.types.Predicate;

public interface PostService {
	Page<Post> getAllPosts();
	Page<Post> getPosts(Predicate predicate, int page, int size, String sorting);
	Optional<Post> getPostById(int id);
	Post savePost(Post post);
	Post updatePost(Post post);
	void deleteById(int id);

}
