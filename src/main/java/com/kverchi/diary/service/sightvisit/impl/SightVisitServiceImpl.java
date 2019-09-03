package com.kverchi.diary.service.sightvisit.impl;

import com.kverchi.diary.model.entity.SightVisit;
import com.kverchi.diary.repository.SightVisitRepository;
import com.kverchi.diary.service.sightvisit.SightVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Liudmyla Melnychuk on 27.8.2019.
 */
@Service
public class SightVisitServiceImpl implements SightVisitService {
    @Autowired
    SightVisitRepository sightVisitRepository;

}
