package com.tribeofspirit.domain.model;

import com.tribeofspirit.domain.model.attribute.Ethnicity;
import com.tribeofspirit.domain.model.attribute.ExpressionType;
import com.tribeofspirit.domain.model.attribute.Gender;
import com.tribeofspirit.domain.model.attribute.MoodType;
import com.tribeofspirit.utils.MathUtil;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import java.util.*;

/**
 * Created by Zea Zhang on 10/31/15.
 */
@Entity
@Table(indexes = {@Index(columnList = "weixinOpenId"), @Index(columnList = "mideaId")})
public class FaceScanningResult extends BaseEntity {

    MoodType mood;

    public Integer getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(Integer moodScore) {
        this.moodScore = moodScore;
    }

    Integer moodScore;
    Integer neutralScore;
    Integer happinessScore;
    Integer sadnessScore;
    Integer suppriseScore;
    Integer angerScore;
    Integer disgustScore;
    Integer fearScore;
    Gender gender;
    Integer age;
    Ethnicity ethnicity;
    Date scanningDate;
    String mideaId;

    String tabooAndAppropriate;

    Integer likeNumber;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    String imageName;

    public String getWeixinOpenId() {
        return weixinOpenId;
    }

    public void setWeixinOpenId(String weixinOpenId) {
        this.weixinOpenId = weixinOpenId;
    }

    String weixinOpenId;

    public String getMideaId() {
        return mideaId;
    }

    public void setMideaId(String mideaId) {
        this.mideaId = mideaId;
    }


    public void setMood(MoodType mood) {
        this.mood = mood;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setScanningDate(Date scanningDate) {
        this.scanningDate = scanningDate;
    }

    public MoodType getMood() {
        return mood;

    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public Date getScanningDate() {
        return scanningDate;
    }

    public void setNeutralScore(Integer neutralScore) {
        this.neutralScore = neutralScore;
    }

    public void setHappinessScore(Integer happinessScore) {
        this.happinessScore = happinessScore;
    }

    public void setSadnessScore(Integer sadnessScore) {
        this.sadnessScore = sadnessScore;
    }

    public void setSuppriseScore(Integer suppriseScore) {
        this.suppriseScore = suppriseScore;
    }

    public void setAngerScore(Integer angerScore) {
        this.angerScore = angerScore;
    }

    public void setDisgustScore(Integer disgustScore) {
        this.disgustScore = disgustScore;
    }

    public void setFearScore(Integer fearScore) {
        this.fearScore = fearScore;
    }

    public Integer getNeutralScore() {
        return neutralScore;
    }

    public Integer getHappinessScore() {
        return happinessScore;
    }

    public Integer getSadnessScore() {
        return sadnessScore;
    }

    public Integer getSuppriseScore() {
        return suppriseScore;
    }

    public Integer getAngerScore() {
        return angerScore;
    }

    public Integer getDisgustScore() {
        return disgustScore;
    }

    public Integer getFearScore() {
        return fearScore;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Transient
    public ExpressionType getMaxExpression() {

        List<ExpressionScore> expressionScoreList =
                Arrays.asList(
                        new ExpressionScore(neutralScore, ExpressionType.NEUTRAL),
                        new ExpressionScore(happinessScore, ExpressionType.HAPPINESS),
                        new ExpressionScore(sadnessScore, ExpressionType.SADNESS),
                        new ExpressionScore(suppriseScore, ExpressionType.SURPRISE),
                        new ExpressionScore(angerScore, ExpressionType.ANGER),
                        new ExpressionScore(disgustScore, ExpressionType.DISGUST),
                        new ExpressionScore(fearScore, ExpressionType.FEAR));

        Collections.sort(expressionScoreList);

        return expressionScoreList.get(6).getExpressionType();
    }

    @Transient
    public Integer getMaxScore() {
        return MathUtil.max(neutralScore, happinessScore, sadnessScore, suppriseScore, angerScore, disgustScore, fearScore);
    }

    @Transient
    public int getMaxExpressionLevel() {
        int maxScore = getMaxScore();
        return (maxScore > 66) ? 3 : (maxScore > 33) ? 2 : 1 ;
    }

    public void setTabooAndAppropriate(String tabooAndAppropriate) {
        this.tabooAndAppropriate = tabooAndAppropriate;
    }

    @Transient
    public String getTabooAndAppropriate() {
        return tabooAndAppropriate;
    }

    private static class ExpressionScore implements Comparable<ExpressionScore> {

        Integer score;
        ExpressionType expressionType;

        public ExpressionScore(Integer score, ExpressionType expressionType) {
            this.score = score;
            this.expressionType = expressionType;
        }

        @Override
        public int compareTo(ExpressionScore another) {
            return score.compareTo(another.score);
        }

        public ExpressionType getExpressionType() {
            return expressionType;
        }
    }
}
