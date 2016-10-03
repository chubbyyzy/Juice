package com.tribeofspirit.domain.model.attribute;

import com.tribeofspirit.common.enums.TitleEnum;

/**
 * Author : gonwang
 * Create time : 2015/12/4.
 */
public enum  ExpressionType implements TitleEnum {

    NEUTRAL("Neutral"), HAPPINESS("Happiness"), SADNESS("Sadness"), SURPRISE("Surprise"), ANGER("Anger"), DISGUST("Disgust"), FEAR("Fear");

    private String title;

    ExpressionType(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
