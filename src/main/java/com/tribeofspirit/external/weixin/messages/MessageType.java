package com.tribeofspirit.external.weixin.messages;

/**
 * Author : gonwang
 * Create time : 2015/11/22.
 */
public enum MessageType {

    TEXT, IMAGE, VOICE, VIDEO, MUSIC, NEWS, MPNEWS, WXCARD;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
