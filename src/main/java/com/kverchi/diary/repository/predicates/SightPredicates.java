package com.kverchi.diary.repository.predicates;

import com.kverchi.diary.model.entity.QSight;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * Created by Liudmyla Melnychuk on 28.8.2019.
 */
public class SightPredicates {
    private static QSight qSight = QSight.sight;

    public static Predicate byCountryCode(String countryCode) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSight.country.countryCode.eq(countryCode));
        return builder;
    }

    public static Predicate byLabel(String label) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSight.label.eq(label));
        return builder;
    }

    public static Predicate inDescription(String text) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSight.description.like(text));
        return builder;
    }
    public static Predicate inLabel(String text) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSight.label.likeIgnoreCase("%" + text + "%"));
        return builder;
    }
}
