package com.tribeofspirit.domain.model.attribute;

/**
 * Created by Zea Zhang on 10/31/15.
 */
public enum MoodType {
    Positive("Positive"),Negative("Negative");

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String value;



    private MoodType(String value){
        this.value = value;
    }

}
