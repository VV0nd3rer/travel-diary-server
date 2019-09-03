package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.*;
import com.kverchi.diary.repository.predicates.SightPredicates;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 28.8.2019.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SightRepositoryTest {
    private static QSight sight = QSight.sight;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    SightRepository sightRepository;

    @Test
    public void testFindByCountryCodeAndLabel() {
        Country country = new Country("CZ");
        entityManager.persistAndFlush(country);
        Sight sight1 = new Sight();
        sight1.setLabel("Test sight 1");
        sight1.setDescription("Description 1");
        sight1.setCountry(country);
        entityManager.persistAndFlush(sight1);

        Sight sight2 = new Sight();
        sight2.setLabel("Test sight 2");
        sight2.setDescription("Description 2");
        sight2.setCountry(country);
        entityManager.persistAndFlush(sight2);

        Sight sight3 = new Sight();
        sight3.setLabel("Test sight 3");
        sight3.setDescription("Description 3");
        sight3.setCountry(country);
        entityManager.persistAndFlush(sight3);

        Pageable pageable = PageRequest.of(0, 2);
        Predicate countryCodePredicate = SightPredicates.byCountryCode("CZ");
        Predicate labelPredicate = SightPredicates.byLabel("Test sight 3");

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(countryCodePredicate).and(labelPredicate);
        Page<Sight> result = sightRepository.findAll(builder, pageable);

        assertEquals(result.getContent().get(0).getLabel(), "Test sight 3");

    }
    @Test
    public void findInDescriptionAndByLabel() {
        Sight sight1 = new Sight();
        sight1.setLabel("Test sight 1");
        sight1.setDescription("Description 1");
        entityManager.persistAndFlush(sight1);

        Sight sight2 = new Sight();
        sight2.setLabel("Test sight 2");
        sight2.setDescription("Description 2");
        entityManager.persistAndFlush(sight2);

        Sight sight3 = new Sight();
        sight3.setLabel("Test sight 3");
        sight3.setDescription("Description 3");
        entityManager.persistAndFlush(sight3);

        Pageable pageable = PageRequest.of(0, 5);
        Predicate byLabel = SightPredicates.byLabel("Test sight 3");
        Predicate inTextPredicate = SightPredicates.inDescription("%escrip%");

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(byLabel).and(inTextPredicate);
        Page<Sight> result = sightRepository.findAll(builder, pageable);

        assertEquals(result.getContent().get(0).getLabel(), "Test sight 3");
    }
    @Test
    public void findSightsWithVisitsCounter() {
        Sight sight1 = new Sight();
        sight1.setLabel("Test sight 1");
        sight1.setDescription("Description 1");
        SightVisitsCounter counter1 = new SightVisitsCounter(5);
        counter1.setSight(sight1);
        entityManager.persistAndFlush(counter1);
        sight1.setSightVisitsCounter(counter1);
        entityManager.persistAndFlush(sight1);

        Sight sight2 = new Sight();
        sight2.setLabel("Test sight 2");
        sight2.setDescription("Description 2");
        SightVisitsCounter counter2 = new SightVisitsCounter(3);
        counter2.setSight(sight2);
        entityManager.persistAndFlush(counter2);
        sight2.setSightVisitsCounter(counter2);
        entityManager.persistAndFlush(sight2);

        Sight sight3 = new Sight();
        sight3.setLabel("Test sight 3");
        sight3.setDescription("Description 3");
        SightVisitsCounter counter3 = new SightVisitsCounter(1);
        counter3.setSight(sight3);
        entityManager.persistAndFlush(counter3);
        sight3.setSightVisitsCounter(counter3);
        entityManager.persistAndFlush(sight3);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("sightVisitsCounter.counter").descending());

        Page<Sight> result = sightRepository.findAll(pageable);

        assertEquals(result.getContent().get(0).getLabel(), "Test sight 1");
    }
}