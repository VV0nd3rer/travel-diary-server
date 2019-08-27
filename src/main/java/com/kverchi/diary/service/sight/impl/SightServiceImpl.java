package com.kverchi.diary.service.sight.impl;

import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.repository.SightRepository;
import com.kverchi.diary.service.sight.SightService;
import com.kverchi.diary.service.country.CountryService;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kverchi.diary.model.entity.Country;

import java.util.List;

/**
 * Created by Kverchi on 3.7.2018.
 */
@Service
public class SightServiceImpl implements SightService {
    private static final Logger logger = LoggerFactory.getLogger(SightServiceImpl.class);
    @Autowired
    SightRepository sightRepository;
    @Autowired
    CountryService countryService;

    @Override
    public Page<Sight> getAllSights() {
        Pageable pageable = Pageable.unpaged();
        Page<Sight> page = sightRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Sight> getSighs(Predicate predicate, int currentPage, int pageSize, String sorting) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Sight> page = sightRepository.findAll(pageable);
        return page;
    }

    @Override
    public List<Sight> getByCountryCode(String countryCode) {
        return sightRepository.findByCountryCountryCode(countryCode);
    }

    @Override
    public Sight getSightById(int sightId) {
        return sightRepository.getOne(sightId);
    }

    @Override
    public Sight updateSight(Sight sight) {
        return sightRepository.save(sight);
    }

    @Override
    public Sight saveSight(Sight sight) {
        Sight addedSight = null;
        Country country = sight.getCountry();
        if(country == null) {
            //TODO handle situation when Map API can't find country code for new sight
            return addedSight;
        }
        Country countryFromDb = countryService.getCountryByCode(country.getCountryCode());
        if(countryFromDb == null) {
            countryService.addCountry(country);
            sight.setCountry(country);
        }
        addedSight =  sightRepository.save(sight);
        return addedSight;
    }

    @Override
    public void deleteById(int sightId) {
        Sight sightToDelete = sightRepository.getOne(sightId);
        sightRepository.delete(sightToDelete);
    }

    @Override
    public Sight findByLatitudeAndLongitude(float latitude, float longitude) {
        return sightRepository.findByLatitudeAndLongitude(latitude, longitude);
    }
}
