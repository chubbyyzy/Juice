package com.tribeofspirit.external.weixin;

/**
 * Author : gonwang
 * Create time : 23.10.2015.
 */
public interface WXContext {

    String getAppId();

    String getSecret();

    String getAccessToken();

    String getJsApiTicket();
}
