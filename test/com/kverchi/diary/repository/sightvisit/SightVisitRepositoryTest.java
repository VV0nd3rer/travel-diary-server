package com.kverchi.diary.repository.sightvisit;

import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.model.entity.SightVisit;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.repository.SightVisitRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 27.8.2019.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SightVisitRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;


}