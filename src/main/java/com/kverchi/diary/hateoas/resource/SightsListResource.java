package com.kverchi.diary.hateoas.resource;

import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.model.entity.SightVisitsCounter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Created by Liudmyla Melnychuk on 2.8.2019.
 */
@Relation(value = "sight", collectionRelation = "sights")
public class SightsListResource extends ResourceSupport {
    private final int sightId;
    private final String label;
    private final String description;
    private final float latitude;
    private final float longitude;
    private final SightVisitsCounter sightVisitsCounter;

    public SightsListResource(Sight sight) {
        this.sightId = sight.getSightId();
        this.label = sight.getLabel();
        this.description = sight.getDescription();
        this.latitude = sight.getLatitude();
        this.longitude = sight.getLongitude();
        this.sightVisitsCounter = sight.getSightVisitsCounter();
    }

    public int getSightId() {
        return sightId;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public SightVisitsCounter getSightVisitsCounter() {
        return sightVisitsCounter;
    }
}
