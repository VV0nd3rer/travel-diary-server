package com.kverchi.diary.service.sight;

import com.kverchi.diary.model.entity.Sight;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Kverchi on 3.7.2018.
 */
public interface SightService {
    Page<Sight> findAll();
    Page<Sight> getSighs(int currentPage, int pageSize);
    List<Sight> findByCountryCode(String countryCode);
    Sight findBySightId(int sightId);
    Sight updateSight(Sight countriesSight);
    Sight addSight(Sight countriesSight);
    void delete(int sightId);
    Sight findByMapCoordXAndMapCoordY(float mapCoordX, float mapCoordY);
}
