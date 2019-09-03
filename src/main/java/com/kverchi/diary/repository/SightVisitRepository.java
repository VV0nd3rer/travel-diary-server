package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.SightVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Liudmyla Melnychuk on 27.8.2019.
 */
@Repository
public interface SightVisitRepository extends JpaRepository <SightVisit, Integer>,
        QuerydslPredicateExecutor<SightVisit> {
}
