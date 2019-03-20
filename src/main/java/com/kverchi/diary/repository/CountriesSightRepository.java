package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.CountriesSight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * Created by Kverchi on 3.7.2018.
 */
@Repository
public interface CountriesSightRepository extends JpaRepository <CountriesSight, Integer>,
        QuerydslPredicateExecutor<CountriesSight> {
    List<CountriesSight> findByCountryCountryCode(String countryCode);
    CountriesSight findByMapCoordXAndMapCoordY(float mapCoordX, float mapCoordY);
}
