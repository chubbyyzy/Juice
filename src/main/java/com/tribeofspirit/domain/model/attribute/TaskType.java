package com.tribeofspirit.domain.model.attribute;

import com.tribeofspirit.common.enums.TitleEnum;

/**
 * Author : gonwang
 * Create time : 2015/12/19.
 */
public enum TaskType implements TitleEnum {

    SPORT("运动"), EAT("吃喝"), RELATION("关系"), INDEPENDENCE("独立");

    private String title;

    TaskType(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public static TaskType valueOfTitle(String title) {
        for (TaskType taskType : values()) {
            if (taskType.title.equals(title)) {
                return taskType;
            }
        }
        return null;
    }
}
