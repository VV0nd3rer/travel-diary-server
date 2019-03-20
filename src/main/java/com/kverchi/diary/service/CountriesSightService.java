package com.kverchi.diary.service;

import com.kverchi.diary.model.entity.CountriesSight;

import java.util.List;

/**
 * Created by Kverchi on 3.7.2018.
 */
public interface CountriesSightService {
    List<CountriesSight> findAll();
    List<CountriesSight> findByCountryCode(String countryCode);
    CountriesSight findBySightId(int sightId);
    CountriesSight updateSight(CountriesSight countriesSight);
    CountriesSight addSight(CountriesSight countriesSight);
    void delete(int sightId);
    CountriesSight findByMapCoordXAndMapCoordY(float mapCoordX, float mapCoordY);
}
