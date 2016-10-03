package com.tribeofspirit.external.weixin.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : gonwang
 * Create time : 2015/11/22.
 */
public class Message {

    private String toUser;

    private MessageType msgType;

    private Text text;

    private Map<String, List<Article>> news;

    private String url;

    public Message(String toUser, Text text) {
        this.toUser = toUser;
        this.msgType = MessageType.TEXT;
        this.text = text;
    }

    public Message(String toUser, List<Article> articles) {
        this.toUser = toUser;
        this.msgType = MessageType.NEWS;
        this.news = new HashMap<String, List<Article>>();
        news.put("articles", articles);
    }

    @JsonProperty("touser")
    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @JsonProperty("msgtype")
    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, List<Article>> getNews() {
        return news;
    }

    public void setNews(Map<String, List<Article>> news) {
        this.news = news;
    }
}
