package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kverchi on 28.6.2018.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, QuerydslPredicateExecutor<Post> {
    List<Post> findByCountriesSightSightId(int sightId);
}
