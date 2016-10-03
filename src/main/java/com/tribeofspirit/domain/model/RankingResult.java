package com.tribeofspirit.domain.model;

/**
 * Created by tihong on 12/15/15.
 */
public class RankingResult {

    private Integer place;
    private String nick_name;
    private String photo_Uri;
    private String image_name;
    private int mood_score;
    private String follower_open_id;

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public RankingResult() {
    }

    public String getPhoto_Uri() {
        return photo_Uri;
    }

    public void setPhoto_Uri(String photo_Uri) {
        this.photo_Uri = photo_Uri;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getMood_score() {
        return mood_score;
    }

    public void setMood_score(int mood_score) {
        this.mood_score = mood_score;
    }

    public String getFollower_open_id() {
        return follower_open_id;
    }

    public void setFollower_open_id(String follower_open_id) {
        this.follower_open_id = follower_open_id;
    }
}
