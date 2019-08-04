package com.kverchi.diary.model.entity;

/**
 * Created by Liudmyla Melnychuk on 11.6.2019.
 */

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "sight_visits")
public class SightVisit {
    @Id
    @Column(name="visit_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int visitId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sight_id")
    private Sight countriesSight;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userId;
    @Column(name="visited_at")
    private ZonedDateTime visitedAt;

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public Sight getCountriesSight() {
        return countriesSight;
    }

    public void setCountriesSight(Sight countriesSight) {
        this.countriesSight = countriesSight;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public ZonedDateTime getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(ZonedDateTime visitedAt) {
        this.visitedAt = visitedAt;
    }
}
