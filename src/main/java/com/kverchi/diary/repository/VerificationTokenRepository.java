package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Liudmyla Melnychuk on 24.9.2019.
 */
public interface VerificationTokenRepository  extends JpaRepository<VerificationToken, Integer> {
    @EntityGraph(value = "verification-token-entity-graph")
    Optional<VerificationToken> findById(int id);

    @EntityGraph(value = "verification-token-entity-graph")
    VerificationToken findByToken(String token);
}
