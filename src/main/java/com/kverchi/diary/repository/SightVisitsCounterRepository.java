package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.SightVisitsCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Liudmyla Melnychuk on 30.8.2019.
 */
@Repository
public interface SightVisitsCounterRepository extends JpaRepository<SightVisitsCounter, Integer>,
        QuerydslPredicateExecutor<SightVisitsCounter> {
}
