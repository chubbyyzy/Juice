package com.tribeofspirit.domain.model.attribute;

import com.tribeofspirit.common.enums.TitleEnum;

/**
 * Created by Zea Zhang on 11/1/15.
 */
public enum ActivityType implements TitleEnum {
    ASSIGNTASK("assigntask",0), LIKE("like",1), EXTORTION("extortion",2), COMFORTFRIEND("comfortfriend",3);

    Integer index;

    @Override
    public String getTitle() {
        return title;
    }

    String title;

    public Integer getIndex() {
        return index;
    }

    ActivityType(String title,Integer index){

        this.title = title;
        this.index = index;
    }

}
