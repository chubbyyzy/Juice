package com.tribeofspirit.external.sightcorp.entity;

import java.util.List;

/**
 * Created by tihong on 10/30/15.
 */
public class Persons {

    private List<Person> persons;
    private long img_height;
    private long img_width;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public long getImg_height() {
        return img_height;
    }

    public void setImg_height(long img_height) {
        this.img_height = img_height;
    }

    public long getImg_width() {
        return img_width;
    }

    public void setImg_width(long img_width) {
        this.img_width = img_width;
    }

}
