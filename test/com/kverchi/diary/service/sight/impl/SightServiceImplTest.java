package com.kverchi.diary.service.sight.impl;

import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.model.entity.SightVisit;
import com.kverchi.diary.repository.SightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 27.8.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SightServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(SightServiceImplTest.class);

    @Autowired
    SightRepository sightRepository;

}