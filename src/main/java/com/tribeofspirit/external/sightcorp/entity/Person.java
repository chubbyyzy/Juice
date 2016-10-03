package com.tribeofspirit.external.sightcorp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by tihong on 10/30/15.
 */
public class Person {

    private Head head;
    private Mood mood;
    private Gender gender;
    private Age age;
    @JsonIgnore
    private List<String> clothingcolors;
    @JsonIgnore
    private Face face;
    private Expressions expressions;
    @JsonIgnore
    private Landmarks landmarks;
    private Ethnicity ethnicity;
    private String imageName;

    public Long getScanningId() {
        return scanningId;
    }

    public void setScanningId(Long scanningId) {
        this.scanningId = scanningId;
    }

    private Long scanningId;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public List<String> getClothingcolors() {
        return clothingcolors;
    }

    public void setClothingcolors(List<String> clothingcolors) {
        this.clothingcolors = clothingcolors;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }


    public Landmarks getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(Landmarks landmarks) {
        this.landmarks = landmarks;
    }

    public Expressions getExpressions() {
        return expressions;
    }

    public void setExpressions(Expressions expressions) {
        this.expressions = expressions;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
