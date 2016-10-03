package com.tribeofspirit.domain.model;

import com.tribeofspirit.domain.model.attribute.ActivityType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Zea Zhang on 11/1/15.
 */
@Entity
public class Activity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }

    ActivityType activityType;

    public ActivityType getActivityType() {
        return activityType;
    }

    String wxOpenId;
    String activityComments;
    Long scanningId;

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getActivityComments() {
        return activityComments;
    }

    public void setActivityComments(String activityComments) {
        this.activityComments = activityComments;
    }

    public Long getScanningId() {
        return scanningId;
    }

    public void setScanningId(Long scanningId) {
        this.scanningId = scanningId;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    Date activityTime;
}
