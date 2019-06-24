package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.Post;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kverchi on 28.6.2018.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, QuerydslPredicateExecutor<Post> {
    @Query("SELECT new com.kverchi.diary.model.entity.Post(p.postId, p.title, " +
            "CASE WHEN (p.previewImageUrl IS NULL) THEN SUBSTRING(p.text, 1, 50) " +
            "ELSE SUBSTRING(p.text, 1, 30) " +
            "END, " +
            "p.previewImageUrl, p.countriesSight, p.author, p.createdAt) from Post p")
    Page<Post> findPostsPreview(Predicate predicate, Pageable pageable);
}
