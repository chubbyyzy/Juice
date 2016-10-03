package com.tribeofspirit.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by tihong on 12/6/15.
 */
@Entity
public class Friendship extends BaseEntity {

    public Friendship(){}

    public Friendship(String userOpenId, String followreWeixinId) {
        this.followerOpenId = followreWeixinId;
        this.userOpenId = userOpenId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }

    private String followerOpenId;

    private String userOpenId;

    public String getFollowerOpenId() {
        return followerOpenId;
    }

    public void setFollowerOpenId(String followerOpenId) {
        this.followerOpenId = followerOpenId;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }
}
