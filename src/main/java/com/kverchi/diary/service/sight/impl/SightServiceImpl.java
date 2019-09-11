package com.kverchi.diary.service.sight.impl;

import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.repository.SightRepository;
import com.kverchi.diary.repository.SightVisitRepository;
import com.kverchi.diary.repository.predicates.SightPredicates;
import com.kverchi.diary.service.sight.SightService;
import com.kverchi.diary.service.country.CountryService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.kverchi.diary.model.entity.Country;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kverchi on 3.7.2018.
 */
@Service
@Transactional
public class SightServiceImpl implements SightService {
    private static final Logger logger = LoggerFactory.getLogger(SightServiceImpl.class);

    public static final String SORT_BY_VISITS = "sightVisitsCounter.counter";
    public static final String SORT_BY_LABEL = "label";

    @Autowired
    SightRepository sightRepository;
    @Autowired
    SightVisitRepository sightVisitRepository;
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
        Sort sort = getSorting(sorting);
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
        Page<Sight> page = sightRepository.findAll(predicate, pageable);
        return page;
    }

    @Override
    public Page<Sight> getSighs(Predicate predicate, String text, int currentPage, int pageSize, String sorting) {
        Sort sort = getSorting(sorting);
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
        BooleanBuilder builder = new BooleanBuilder();

        Predicate searchInLabelPredicate = SightPredicates.inLabel(text);
        builder.and(predicate).and(searchInLabelPredicate);

        Page<Sight> page = sightRepository.findAll(builder, pageable);
        return page;
    }

    private Sort getSorting(String sorting) {
        Sort sort = Sort.unsorted();
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        SightSortingCriteria sortingType = SightSortingCriteria.valueOf(sorting.toUpperCase());
        switch (sortingType) {
            case UNSORTED:
                break;
            case MOST_VISITED:
                orders.add(new Sort.Order(Sort.Direction.DESC, SORT_BY_VISITS, Sort.NullHandling.NULLS_LAST));
                sort = Sort.by(orders);
                break;
            case LEAST_VISITED:
                orders.add(new Sort.Order(Sort.Direction.ASC, SORT_BY_VISITS, Sort.NullHandling.NULLS_FIRST));
                sort = Sort.by(orders);
                break;
            case BY_LABEL_ASC:
                sort = Sort.by(SORT_BY_LABEL);
                break;
            case BY_LABEL_DESC:
                sort = Sort.by(SORT_BY_LABEL).descending();
                break;
        }
        return sort;
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
        if (country == null) {
            //TODO handle situation when Map API can't find country code for new sight
            return addedSight;
        }
        Country countryFromDb = countryService.getCountryByCode(country.getCountryCode());
        if (countryFromDb == null) {
            countryService.addCountry(country);
            sight.setCountry(country);
        }
        addedSight = sightRepository.save(sight);
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
