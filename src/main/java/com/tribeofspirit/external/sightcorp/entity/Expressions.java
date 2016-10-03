package com.tribeofspirit.external.sightcorp.entity;

/**
 * Created by tihong on 10/30/15.
 */
public class Expressions {


    private Emotion sadness;
    private Emotion neutral;
    private Emotion disgust;
    private Emotion anger;
    private Emotion surprise;
    private Emotion fear;
    private Emotion happiness;

    public Emotion getSadness() {
        return sadness;
    }

    public void setSadness(Emotion sadness) {
        this.sadness = sadness;
    }

    public Emotion getNeutral() {
        return neutral;
    }

    public void setNeutral(Emotion neutral) {
        this.neutral = neutral;
    }

    public Emotion getDisgust() {
        return disgust;
    }

    public void setDisgust(Emotion disgust) {
        this.disgust = disgust;
    }

    public Emotion getAnger() {
        return anger;
    }

    public void setAnger(Emotion anger) {
        this.anger = anger;
    }

    public Emotion getSurprise() {
        return surprise;
    }

    public void setSurprise(Emotion surprise) {
        this.surprise = surprise;
    }

    public Emotion getFear() {
        return fear;
    }

    public void setFear(Emotion fear) {
        this.fear = fear;
    }

    public Emotion getHappiness() {
        return happiness;
    }

    public void setHappiness(Emotion happiness) {
        this.happiness = happiness;
    }

    public class Emotion{
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
