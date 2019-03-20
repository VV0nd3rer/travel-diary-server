package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.OauthClientCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Liudmyla Melnychuk on 19.2.2019.
 */
@Repository
public interface OauthClientCredentialsRepository extends JpaRepository<OauthClientCredentials, Integer> {
    List<OauthClientCredentials> findByCredentialsEmail(String credentialsEmail);
}
