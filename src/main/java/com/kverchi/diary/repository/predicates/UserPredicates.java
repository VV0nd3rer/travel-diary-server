package com.kverchi.diary.repository.predicates;

import com.kverchi.diary.model.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * Created by Liudmyla Melnychuk on 15.9.2019.
 */
public class UserPredicates {
    private static QUser qUser = QUser.user;

    public static Predicate searchLikeUsername(String text) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qUser.username.likeIgnoreCase("%" + text + "%"));
        return builder;
    }
}
