package com.tribeofspirit.domain.model.attribute;

/**
 * Created by Zea Zhang on 10/31/15.
 */
public enum Ethnicity {
    Asian("Asian"), Caucasian("Caucasian"), African("African"), Hispanic("Hispanic");

    private String value;

    private Ethnicity(String ethnicity)
    {
        this.value = ethnicity;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;

    }

}
