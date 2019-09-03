package com.kverchi.diary.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by Liudmyla Melnychuk on 30.8.2019.
 */
@Entity
@Table(name="sight_visits_counter")
public class SightVisitsCounter {
    @Id
    @Column(name="sight_id")
    private int sightId;
    @Column(name="counter")
    private int counter;
    @OneToOne
    @JoinColumn(name="sight_id")
    @MapsId
    @JsonBackReference
    private Sight sight;

    public SightVisitsCounter() { }
    public SightVisitsCounter(int counter) {
        this.counter = counter;
    }
    public int getSightId() {
        return sightId;
    }

    public void setSightId(int sightId) {
        this.sightId = sightId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Sight getSight() {
        return sight;
    }

    public void setSight(Sight sight) {
        this.sight = sight;
    }
}
