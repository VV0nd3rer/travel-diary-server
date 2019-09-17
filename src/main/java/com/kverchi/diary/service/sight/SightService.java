package com.kverchi.diary.service.sight;

import com.kverchi.diary.model.entity.Sight;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Kverchi on 3.7.2018.
 */
public interface SightService {
    Page<Sight> getAllSights();
    Page<Sight> getAllSights(String searchLikeAttr);
    Page<Sight> getSighs(Predicate predicate, int currentPage, int pageSize, String sorting);
    Page<Sight> getSighs(Predicate predicate, String searchLikeAttr, int currentPage, int pageSize, String sorting);
    Sight getSightById(int sightId);
    Sight getSightByLabel(String label);
    Sight updateSight(Sight sight);
    Sight saveSight(Sight sight);
    void deleteById(int sightId);
    Sight findByLatitudeAndLongitude(float latitude, float longitude);
}
