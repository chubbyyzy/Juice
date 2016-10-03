package com.tribeofspirit.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Zea Zhang on 10/31/15.
 */
@Entity
public class User extends BaseEntity {

    String webChatOpenId;
    String juiceId;
    String nickName;
    String photoUri;
    Integer gender;
    String city;
    String province;
    boolean isSubscribe;
    String groupId;

    public void setWebChatOpenId(String webChatOpenId) {
        this.webChatOpenId = webChatOpenId;
    }

    public void setJuiceId(String juiceId) {
        this.juiceId = juiceId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSubscribDate(Date subscribDate) {
        this.subscribDate = subscribDate;
    }

    public String getWebChatOpenId() {

        return webChatOpenId;
    }

    public String getJuiceId() {
        return juiceId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public Integer getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getLanguage() {
        return language;
    }

    public Date getSubscribDate() {
        return subscribDate;
    }

    public boolean isSubscribe() {
        return isSubscribe;
    }

    public void setSubscribe(boolean subscribe) {
        isSubscribe = subscribe;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    String language;
    Date subscribDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }
}
