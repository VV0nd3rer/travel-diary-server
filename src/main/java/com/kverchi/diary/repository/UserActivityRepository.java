package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.UserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kverchi on 14.8.2018.
 */
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivityLog, String> {
    List<UserActivityLog> findByUserIdOrderByLoginTimeDesc(int userId);
}
