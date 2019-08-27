package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.SightVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * Created by Liudmyla Melnychuk on 27.8.2019.
 */
public interface SightVisitRepository extends JpaRepository <SightVisit, Integer>,
        QuerydslPredicateExecutor<SightVisit> {
    List<SightVisit> findBySightSightId(int sightId);
    int countBySightSightId(int sightid);
}
