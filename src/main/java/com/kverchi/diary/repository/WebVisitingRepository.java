package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.WebVisitingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kverchi on 14.8.2018.
 */
@Repository
public interface WebVisitingRepository extends JpaRepository<WebVisitingLog, Integer> {
}
