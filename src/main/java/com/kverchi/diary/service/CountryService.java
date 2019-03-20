package com.kverchi.diary.service;

import com.kverchi.diary.model.entity.Country;

/**
 * Created by Kverchi on 13.8.2018.
 */
public interface CountryService {
    Country getCountryByCode(String countryCode);
    Country addCountry(Country country);
}
