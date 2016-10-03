package com.tribeofspirit.external.weixin;

import com.fasterxml.jackson.databind.JsonNode;
import com.tribeofspirit.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * Author : gonwang
 * Create time : 23.10.2015.
 */
@Component("wbxContext")
public class WXContextImpl implements WXContext, InitializingBean {

    public static final String ACCESS_TOKEN_NODE_NAME = "access_token";

    public static final String TICKET_NODE_NAME = "ticket";

    private String appId;

    private String secret;

    private String accessTokenUrl;

    private String jsApiTicketUrl;

    private String accessToken;

    private String jsApiTicket;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("#{weixin['appId']}")
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Value("#{weixin['secret']}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("#{weixin['accessTokenUrl']}")
    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    @Value("#{weixin['jsApiTicketUrl']}")
    public void setJsApiTicketUrl(String jsApiTicketUrl) {
        this.jsApiTicketUrl = jsApiTicketUrl;
    }

    public String getAccessToken() {

        return accessToken;
    }

    public String getJsApiTicket() {
        return jsApiTicket;
    }

    public String getAppId() {
        return appId;
    }

    public String getSecret() {
        return secret;
    }

    private String refreshAccessToken() {

        String request = MessageFormat.format(accessTokenUrl, appId, secret);

        JsonNode responseNode = HttpClientUtils.sendSSLRequest(request);

        if (responseNode == null) {
            logger.error("Failed to refresh access token.");
            return null;
        }

        JsonNode accessTokenNode = responseNode.get(ACCESS_TOKEN_NODE_NAME);
        if (accessTokenNode == null) {
            logger.error("Failed to refresh access token : {}", responseNode.asText());
            return null;
        }

        return responseNode.get(ACCESS_TOKEN_NODE_NAME).textValue();
    }


    private String refreshTicket(String accessToken) {

        String request = MessageFormat.format(jsApiTicketUrl, accessToken);

        JsonNode responseNode = HttpClientUtils.sendSSLRequest(request);

        if (responseNode == null) {
            logger.error("Failed to refresh jsApiTicket.");
            return null;
        }

        JsonNode jsApiTicketNode = responseNode.get(TICKET_NODE_NAME);
        if (jsApiTicketNode == null) {
            logger.error("Failed to refresh jsApiTicket : {}", responseNode.asText());
            return null;
        }

        return responseNode.get(TICKET_NODE_NAME).textValue();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        refreshContext();
    }

    @Scheduled(cron="0 0 * * * ?")
    public void refreshContext() {

        logger.info("Start to refresh WXContext.");

        String accessToken = refreshAccessToken();

        if (accessToken == null) return ;

        String jsApiTicket = refreshTicket(accessToken);

        logger.info("New access token : {}.", accessToken);
        this.accessToken = accessToken;

        logger.info("New jsApiTicket : {}.", jsApiTicket);
        this.jsApiTicket = jsApiTicket;

        logger.info("Refresh WXContext successfully.");
    }
}
