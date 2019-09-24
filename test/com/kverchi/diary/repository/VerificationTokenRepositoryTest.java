package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.model.entity.VerificationToken;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 24.9.2019.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class VerificationTokenRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Test
    public void testAddToken() {
        User user = new User("user1", "test", "test@mail.com");
        entityManager.persistAndFlush(user);
        VerificationToken verificationToken = new VerificationToken("lksdioielkfnkld", user);
        entityManager.persistAndFlush(verificationToken);
        VerificationToken createdToken = null;
        Optional<VerificationToken> optional = verificationTokenRepository.findById(1);
        if(optional.isPresent()) {
            createdToken = optional.get();
        }

        assertNotNull(createdToken);
    }
}