package com.kverchi.diary.model.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Liudmyla Melnychuk on 18.9.2017.
 */
@Entity
@Table(name="user_activity_log")
public class UserActivityLog {
    @Id
    @Column(name="session_id")
    private String sessionId;
    @Column(name="user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name="login_time", insertable = false, updatable = false)
    private ZonedDateTime loginTime;
    @Column(name="login_ip")
    private String loginIp;
    @Column(name="user_hostname")
    private String hostname;
    @Column(name="active_session")
    private boolean activeSession;

    @Column(name="os")
    private String osInfo;
    @Column(name="browser")
    private String browserInfo;
    @Column(name="user_agent")
    private String userAgentInfo;

    public String getOsInfo() {
        return osInfo;
    }

    public void setOsInfo(String osInfo) {
        this.osInfo = osInfo;
    }

    public String getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }

    public String getUserAgentInfo() {
        return userAgentInfo;
    }

    public void setUserAgentInfo(String userAgentInfo) {
        this.userAgentInfo = userAgentInfo;
    }

    public boolean isActiveSession() {
        return activeSession;
    }

    public void setActiveSession(boolean activeSession) {
        this.activeSession = activeSession;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ZonedDateTime getLoginTime() {
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        String text = loginTime.format(formatter);
        loginTime = ZonedDateTime.parse(text, formatter);
        return loginTime;
    }

    public void setLoginTime(ZonedDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
