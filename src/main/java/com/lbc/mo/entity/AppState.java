package com.lbc.mo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
@Entity
@Table(name = "app_state")
public class AppState {
    @Id
    @GeneratedValue
    private Integer id;

    private String user;
    @Column(name = "app_id")
    private String appId;

    private String state;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Transient
    private Date startDay;
    @Transient
    private Date endDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStartTime() {
        if (startTime != null) return (Date) startTime.clone();
        return null;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime == null ? null : (Date) startTime.clone();
    }

    public Date getEndTime() {
        if (endTime != null) return (Date) endTime.clone();
        return null;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime == null ? null : (Date) endTime.clone();
    }

    public Date getStartDay() {
        if (startDay != null) return (Date) startDay.clone();
        return null;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay == null ? null : (Date) startDay.clone();
    }

    public Date getEndDay() {
        if (endDay != null) return (Date) endDay.clone();
        return null;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay == null ? null : (Date) endDay.clone();
    }
}
