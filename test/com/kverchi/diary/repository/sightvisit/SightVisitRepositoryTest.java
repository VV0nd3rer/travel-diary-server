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

    @Autowired
    SightVisitRepository sightVisitRepository;
    
    @Test
    public void testCountSightVisits() {
        User user1 = new User();
        user1.setUsername("user1");
        entityManager.persistAndFlush(user1);

        User user2 = new User();
        user2.setUsername("user2");
        entityManager.persistAndFlush(user2);

        Sight sight = new Sight();
        sight.setLabel("Test");
        entityManager.persistAndFlush(sight);

        SightVisit sightVisit1 = new SightVisit();
        sightVisit1.setSight(sight);
        sightVisit1.setUser(user1);
        entityManager.persistAndFlush(sightVisit1);

        SightVisit sightVisit2 = new SightVisit();
        sightVisit2.setSight(sight);
        sightVisit2.setUser(user2);
        entityManager.persistAndFlush(sightVisit2);


        int sightVisitsCount = sightVisitRepository.countBySightSightId(sight.getSightId());
        assertEquals(sightVisitsCount, 2);
    }
}