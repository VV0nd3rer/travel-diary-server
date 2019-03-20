package com.kverchi.diary.service.impl;

import com.kverchi.diary.model.entity.CountriesSight;
import com.kverchi.diary.repository.CountriesSightRepository;
import com.kverchi.diary.service.CountriesSightService;
import com.kverchi.diary.service.CountryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kverchi.diary.model.entity.Country;

import java.util.List;

/**
 * Created by Kverchi on 3.7.2018.
 */
@Service
public class CountriesSightServiceImpl implements CountriesSightService {
    final static Logger logger = LogManager.getLogger(CountriesSightServiceImpl.class);
    @Autowired
    CountriesSightRepository countriesSightRepository;
    @Autowired
    CountryService countryService;

    @Override
    public List<CountriesSight> findAll() {
        return countriesSightRepository.findAll();
    }

    @Override
    public List<CountriesSight> findByCountryCode(String countryCode) {
        return countriesSightRepository.findByCountryCountryCode(countryCode);
    }

    @Override
    public CountriesSight findBySightId(int sightId) {
        return countriesSightRepository.getOne(sightId);
    }

    @Override
    public CountriesSight updateSight(CountriesSight countriesSight) {
        return countriesSightRepository.save(countriesSight);
    }

    @Override
    public CountriesSight addSight(CountriesSight countriesSight) {
        CountriesSight addedSight = null;
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
        CountriesSight sightToDelete = countriesSightRepository.getOne(sightId);
        countriesSightRepository.delete(sightToDelete);
    }

    @Override
    public CountriesSight findByMapCoordXAndMapCoordY(float mapCoordX, float mapCoordY) {
        return countriesSightRepository.findByMapCoordXAndMapCoordY(mapCoordX, mapCoordY);
    }
}
