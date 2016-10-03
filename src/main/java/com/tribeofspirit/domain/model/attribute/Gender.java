package com.tribeofspirit.domain.model.attribute;

/**
 * Created by Zea Zhang on 10/31/15.
 */
public enum Gender {
    Male("Male"), Female("Female");

    private String value;

    private Gender(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
