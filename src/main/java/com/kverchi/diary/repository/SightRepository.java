package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * Created by Kverchi on 3.7.2018.
 */
@Repository
public interface SightRepository extends JpaRepository <Sight, Integer>,
        QuerydslPredicateExecutor<Sight> {
    Sight findByLatitudeAndLongitude(float latitude, float longitude);

}
