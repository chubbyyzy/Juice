package com.tribeofspirit.external.weixin.messages;

/**
 * Author : gonwang
 * Create time : 2015/11/22.
 */
public class Text {

    private String content;

    public Text() {
    }

    public Text(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Text valueOf(String content) {
        return new Text(content);
    }
}
