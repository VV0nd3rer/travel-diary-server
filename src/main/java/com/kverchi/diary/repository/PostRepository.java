package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.Post;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


/**
 * Created by Kverchi on 28.6.2018.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, QuerydslPredicateExecutor<Post> {
    @Override
    @EntityGraph(value = "post-entity-graph")
    Page<Post> findAll(Pageable pageable);

    @Override
    @EntityGraph(value = "post-entity-graph")
    Page<Post> findAll(Predicate predicate, Pageable pageable);
}
