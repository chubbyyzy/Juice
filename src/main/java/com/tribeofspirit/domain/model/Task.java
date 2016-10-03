package com.tribeofspirit.domain.model;

import com.tribeofspirit.domain.model.attribute.EffectLevel;
import com.tribeofspirit.domain.model.attribute.ExpressionType;
import com.tribeofspirit.domain.model.attribute.TaskType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author : gonwang
 * Create time : 2015/12/19.
 */
@Entity
public class Task extends BaseEntity {

    private String description;

    TaskType type;

    private EffectLevel effectLevel;

    private ExpressionType expressionType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public EffectLevel getEffectLevel() {
        return effectLevel;
    }

    public void setEffectLevel(EffectLevel effectLevel) {
        this.effectLevel = effectLevel;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }
}
