package com.kverchi.diary.service.sight.impl;

import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.repository.CountriesSightRepository;
import com.kverchi.diary.service.sight.SightService;
import com.kverchi.diary.service.country.CountryService;
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
    CountriesSightRepository countriesSightRepository;
    @Autowired
    CountryService countryService;

    @Override
    public Page<Sight> findAll() {
        Pageable pageable = Pageable.unpaged();
        Page<Sight> page = countriesSightRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Sight> getSighs(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Sight> page = countriesSightRepository.findAll(pageable);
        return page;
    }

    @Override
    public List<Sight> findByCountryCode(String countryCode) {
        return countriesSightRepository.findByCountryCountryCode(countryCode);
    }

    @Override
    public Sight findBySightId(int sightId) {
        return countriesSightRepository.getOne(sightId);
    }

    @Override
    public Sight updateSight(Sight countriesSight) {
        return countriesSightRepository.save(countriesSight);
    }

    @Override
    public Sight addSight(Sight countriesSight) {
        Sight addedSight = null;
        Country country = countriesSight.getCountry();
        if(country == null) {
            //TODO handle situation when Map API can't find country code for new sight
            return addedSight;
        }
        Country countryFromDb = countryService.getCountryByCode(country.getCountryCode());
        if(countryFromDb == null) {
            countryService.addCountry(country);
            countriesSight.setCountry(country);
        }
        addedSight =  countriesSightRepository.save(countriesSight);
        return addedSight;
    }

    @Override
    public void delete(int sightId) {
        Sight sightToDelete = countriesSightRepository.getOne(sightId);
        countriesSightRepository.delete(sightToDelete);
    }

    @Override
    public Sight findByMapCoordXAndMapCoordY(float mapCoordX, float mapCoordY) {
        return countriesSightRepository.findByMapCoordXAndMapCoordY(mapCoordX, mapCoordY);
    }
}
