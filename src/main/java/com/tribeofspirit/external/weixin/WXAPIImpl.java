package com.tribeofspirit.external.weixin;

import com.fasterxml.jackson.databind.JsonNode;
import com.tribeofspirit.domain.model.FaceScanningResult;
import com.tribeofspirit.domain.model.User;
import com.tribeofspirit.domain.model.attribute.MoodType;
import com.tribeofspirit.external.weixin.messages.Article;
import com.tribeofspirit.external.weixin.messages.Message;
import com.tribeofspirit.external.weixin.messages.MessageType;
import com.tribeofspirit.external.weixin.messages.Text;
import com.tribeofspirit.utils.HttpClientUtils;
import com.tribeofspirit.utils.JsonUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


/**
 * Author : gonwang
 * Create time : 20.10.2015.
 */
@Component
public class WXAPIImpl implements WXAPI, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private WXContext wxContext;

    private String imgUrl;

    private String tempFilePath;

    private String openIdUrl;

    private String messageUrl;

    private String detectTip;

    private String userInfoUrl;

    private String embraceTip;

    private String treatTip;

    private String admireTip;

    private String encourageTip;

    private String taskTip;

    private String rankingTip;

    @Value("#{weixin['imageUrl']}")
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Value("#{weixin['tempFileDir']}")
    public void setTempFilePath(String tempFilePath) {
        this.tempFilePath = tempFilePath;
    }

    @Value("#{weixin['messageUrl']}")
    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    @Value("#{weixin['openIdUrl']}")
    public void setOpenIdUrl(String openIdUrl) {
        this.openIdUrl = openIdUrl;
    }

    @Value("#{msg['detect.tip']}")
    public void setDetectTip(String detectTip) {
        this.detectTip = detectTip;
    }

    @Value("#{weixin['userInfoUrl']}")
    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    @Value("#{msg['friend.embrace']}")
    public void setEmbraceTip(String embraceTip) {
        this.embraceTip = embraceTip;
    }

    @Value("#{msg['friend.treat']}")
    public void setTreatTip(String treatTip) {
        this.treatTip = treatTip;
    }

    @Value("#{msg['friend.admire']}")
    public void setAdmireTip(String admireTip) {
        this.admireTip = admireTip;
    }

    @Value("#{msg['friend.encourage']}")
    public void setEncourageTip(String encourageTip) {
        this.encourageTip = encourageTip;
    }

    @Value("#{msg['friend.task']}")
    public void setTaskTip(String taskTip) {
        this.taskTip = taskTip;
    }

    @Value("#{msg['ranking.tip']}")
    public void setRankingTip(String rankingTip) {
        this.rankingTip = rankingTip;
    }

    @Autowired
    public void setWxContext(WXContext wxContext) {
        this.wxContext = wxContext;
    }

    public File getImage(String mediaId) {

        String accessToken = wxContext.getAccessToken();

        String filename = RandomStringUtils.random(32, true, true);

        Path path = Paths.get(tempFilePath + File.separatorChar + filename + ".jpg");

        String request = MessageFormat.format(imgUrl, accessToken, mediaId);

        HttpClientUtils.requestFile(request, path);

        return path.toFile();
    }

    @Override
    public String getOpenId(String code) {

        String request = MessageFormat.format(openIdUrl, wxContext.getAppId(), wxContext.getSecret(), code);

        JsonNode response = HttpClientUtils.sendSSLRequest(request);

        return response.get("openid").textValue();
    }

    @Override
    public User getUserInfo(String openId){

        String userInfoRequest = MessageFormat.format(userInfoUrl, wxContext.getAccessToken(), openId);

        JsonNode userInfoResponse = HttpClientUtils.sendSSLRequest(userInfoRequest);

        if(userInfoResponse.get("errcode") == null
                || StringUtils.isBlank(userInfoResponse.get("errcode").textValue())){

            User user = new User();
            user.setCity(userInfoResponse.get("city").asText());
            user.setSubscribe(userInfoResponse.get("subscribe").asBoolean());
            user.setNickName(userInfoResponse.get("nickname").textValue());
            user.setWebChatOpenId(openId);
            user.setGender(userInfoResponse.get("sex").asInt());
            user.setPhotoUri(userInfoResponse.get("headimgurl").asText());
            user.setLanguage(userInfoResponse.get("language").asText());
            user.setSubscribDate(new Date(userInfoResponse.get("subscribe_time").asLong()));
            user.setGroupId(userInfoResponse.get("groupid").asText());
            return user;
        }


        return null;
    }

    @Override
    public void sendResultMessage(String weChatOpenId, FaceScanningResult result) {

        MoodType moodType = result.getMood();

        if (moodType == null) {
            logger.error("Cannot send message for {}", result);
            return;
        }

        String symbol = moodType.equals(MoodType.Positive) ? "+" : "-";

        String content = MessageFormat.format(detectTip, symbol, result.getMoodScore());

        String request = prepareRequestUrl();

/*
        Article article = new Article(content, "http://www.sohu.com");
        Message message = new Message(weChatOpenId, Arrays.asList(article));
*/

        Message message = new Message(weChatOpenId, Text.valueOf(createLinkableText("http://www.9dongni.com/result?scanningid="+result.getId(), content)));

        JsonNode response = HttpClientUtils.postSSLRequest(request, JsonUtils.writeAsString(message));

        logger.debug("Send message with response : {}", response);
    }

    private String createLinkableText(String url, String content) {
        return "<a href=\""+url+"\">"+content+"</a>";
    }

    @Override
    public void sendEmbraceMessage(String weChatOpenId, User friend) {

        String content = MessageFormat.format(embraceTip, createPhotoHtml(friend), friend.getNickName());

        String request = prepareRequestUrl();

        Message message = new Message(weChatOpenId, Text.valueOf(content));

        JsonNode response = HttpClientUtils.postSSLRequest(request, JsonUtils.writeAsString(message));

        logger.debug("Send message with response : {}", response);
    }

    private String prepareRequestUrl() {
        return MessageFormat.format(messageUrl, wxContext.getAccessToken());
    }

    private String createPhotoHtml(User friend) {
        return "<img src=\""+friend.getPhotoUri()+"\" />";
    }

    @Override
    public void sendTreatMessage(String weChatOpenId, User friend) {
        String content = MessageFormat.format(treatTip, createPhotoHtml(friend), friend.getNickName());

        String request = prepareRequestUrl();

        Message message = new Message(weChatOpenId, Text.valueOf(content));

        JsonNode response = HttpClientUtils.postSSLRequest(request, JsonUtils.writeAsString(message));

        logger.debug("Send message with response : {}", response);

    }

    @Override
    public void sendAdmireMessage(String weChatOpenId, User friend) {
        String content = MessageFormat.format(admireTip, createPhotoHtml(friend), friend.getNickName());

        String request = prepareRequestUrl();

        Message message = new Message(weChatOpenId, Text.valueOf(content));

        JsonNode response = HttpClientUtils.postSSLRequest(request, JsonUtils.writeAsString(message));

        logger.debug("Send message with response : {}", response);
    }

    @Override
    public void sendEncourageMessage(String weChatOpenId, User friend) {

        String content = MessageFormat.format(encourageTip, createPhotoHtml(friend), friend.getNickName());

        String request = prepareRequestUrl();

        Message message = new Message(weChatOpenId, Text.valueOf(content));

        JsonNode response = HttpClientUtils.postSSLRequest(request, JsonUtils.writeAsString(message));

        logger.debug("Send message with response : {}", response);
    }

    @Override
    public void sendTaskMessage(String weChatOpenId, User friend, String taskUrl) {

        String content = MessageFormat.format(taskTip, createPhotoHtml(friend), friend.getNickName());

        String request = prepareRequestUrl();

        Message message = new Message(weChatOpenId, Text.valueOf(createLinkableText(taskUrl, content)));

        JsonNode response = HttpClientUtils.postSSLRequest(request, JsonUtils.writeAsString(message));

        logger.debug("Send message with response : {}", response);
    }

    @Override
    public void sendRankingMessage(String weChatOpenId, Date date, int myPlace, int myScore, User champion, String rankingUrl) {

        DateFormat dateFormat = new SimpleDateFormat("M.d");

        String content = MessageFormat.format(rankingTip, myPlace, myScore, createPhotoHtml(champion), champion.getNickName(), dateFormat.format(date));

        String request = prepareRequestUrl();

        Message message = new Message(weChatOpenId, Text.valueOf(createLinkableText(rankingUrl, content)));

        JsonNode response = HttpClientUtils.postSSLRequest(request, JsonUtils.writeAsString(message));

        logger.debug("Send message with response : {}", response);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        guarantyTempFilePathExists();
    }

    private void guarantyTempFilePathExists() {

        File file = new File(tempFilePath);

        if (!file.exists()) {

            file.mkdirs();
        }
    }
}
