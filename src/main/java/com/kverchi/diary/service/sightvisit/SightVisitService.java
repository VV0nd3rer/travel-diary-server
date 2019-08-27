package com.kverchi.diary.service.sightvisit;

import com.kverchi.diary.model.entity.SightVisit;

import java.util.List;

/**
 * Created by Liudmyla Melnychuk on 27.8.2019.
 */
public interface SightVisitService {
    List<SightVisit> findVisitsBySightId(int sightId);
}
