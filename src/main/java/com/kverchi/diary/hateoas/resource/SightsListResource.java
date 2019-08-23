package com.kverchi.diary.hateoas.resource;

import com.kverchi.diary.model.entity.Sight;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Created by Liudmyla Melnychuk on 2.8.2019.
 */
@Relation(value = "sight", collectionRelation = "sights")
public class SightsListResource extends ResourceSupport {
    private final int sightId;
    private final String label;
    private final float mapCoordLat;
    private final float mapCoordLong;

    public SightsListResource(Sight sight) {
        this.sightId = sight.getSightId();
        this.label = sight.getLabel();
        this.mapCoordLat = sight.getMapCoordX();
        this.mapCoordLong = sight.getMapCoordY();
    }

    public int getSightId() {
        return sightId;
    }

    public String getLabel() {
        return label;
    }

    public float getMapCoordLat() {
        return mapCoordLat;
    }

    public float getMapCoordLong() {
        return mapCoordLong;
    }
}
