package com.kverchi.diary.model.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Liudmyla Melnychuk on 3.5.2018.
 */
@Entity
@Table(name="web_visiting_log")
public class WebVisitingLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="log_id")
    private int logId;
    @Column(name="user_agent")
    private String userAgent;
    @Column(name="visiting_time")
    private ZonedDateTime visitingTime;
    @Column(name="ip_address")
    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public ZonedDateTime getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(ZonedDateTime visitingTime) {
        this.visitingTime = visitingTime;
    }
    @PrePersist
    public void setVisitingTime() {
        this.visitingTime = ZonedDateTime.now();
    }
}
