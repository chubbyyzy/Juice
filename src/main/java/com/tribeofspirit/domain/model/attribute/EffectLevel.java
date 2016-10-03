package com.tribeofspirit.domain.model.attribute;

import com.tribeofspirit.common.enums.TitleEnum;

/**
 * Author : gonwang
 * Create time : 2015/12/20.
 */
public enum EffectLevel implements TitleEnum {

    SPECIAL("特效"), EFFECTIVE("有用"), PLACEBO("安慰剂"), FUNNY("搞笑"), SPOOF("恶搞");

    private String title;

    EffectLevel(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public static EffectLevel valueOfTitle(String title) {
        for (EffectLevel effectLevel : values()) {
            if (effectLevel.title.equals(title)) {
                return effectLevel;
            }
        }
        return null;
    }
}
