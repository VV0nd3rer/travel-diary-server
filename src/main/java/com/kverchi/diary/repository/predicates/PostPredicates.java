package com.kverchi.diary.repository.predicates;

import com.kverchi.diary.model.entity.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Map;

/**
 * Created by Kverchi on 18.7.2018.
 */
public class PostPredicates {
    private static QPost post = QPost.post;

    public static Predicate searchByAuthorId(int userId) {
        return post.author.userId.eq(userId);
    }

    public static Predicate searchBySightId(int sightId) {
        return post.countriesSight.sightId.eq(sightId);
    }

    public static Predicate searchInPosts(String text) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(post.text.containsIgnoreCase(text)
                .or(post.description.containsIgnoreCase(text)
                        .or(post.title.containsIgnoreCase(text))));
        return builder;
    }

    public static Predicate searchInTitleOnly(String text) {
        return post.title.containsIgnoreCase(text);
    }

}
