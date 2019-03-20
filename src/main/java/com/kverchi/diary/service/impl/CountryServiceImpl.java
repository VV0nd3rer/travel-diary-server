package com.kverchi.diary.service.impl;

import com.kverchi.diary.model.entity.Country;
import com.kverchi.diary.repository.CountryRepository;
import com.kverchi.diary.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kverchi on 13.8.2018.
 */
@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public Country getCountryByCode(String countryCode) {
        return countryRepository.getOne(countryCode);
    }

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }
}
