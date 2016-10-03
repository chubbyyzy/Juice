package com.tribeofspirit.domain.model.attribute;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tribeofspirit.common.enums.TitleEnum;
import com.tribeofspirit.common.web.json.TitleEnumJsonSerializer;

/**
 * User: Lewis Wang
 * Date: 11/8/11
 * Time: 2:02 PM
 */
@JsonSerialize(using = TitleEnumJsonSerializer.class)
public enum EntityStatus implements TitleEnum {

    NORMAL("正常"), LOCKED("禁用");

    private String title;

    EntityStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
