package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kverchi on 3.7.2018.
 */
@Repository
public interface CountryRepository extends JpaRepository <Country, String> {
}
