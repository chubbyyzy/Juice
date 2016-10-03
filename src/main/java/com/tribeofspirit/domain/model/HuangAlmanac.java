package com.tribeofspirit.domain.model;

import com.tribeofspirit.domain.model.attribute.ExpressionType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Zea Zhang on 11/29/15.
 */
@Entity
public class HuangAlmanac extends BaseEntity {

    private ExpressionType expressionType;

    private int expressionLevel;

    private int tabooOrAppropriate;

    private String message;

    public HuangAlmanac() {
    }

    public HuangAlmanac(ExpressionType expressionType, int expressionLevel, int tabooOrAppropriate, String message) {
        this.expressionType = expressionType;
        this.expressionLevel = expressionLevel;
        this.tabooOrAppropriate = tabooOrAppropriate;
        this.message = message;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

    public int getExpressionLevel() {
        return expressionLevel;
    }

    public void setExpressionLevel(int expressionLevel) {
        this.expressionLevel = expressionLevel;
    }

    public int getTabooOrAppropriate() {
        return tabooOrAppropriate;
    }

    public void setTabooOrAppropriate(int tabooOrAppropriate) {
        this.tabooOrAppropriate = tabooOrAppropriate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
