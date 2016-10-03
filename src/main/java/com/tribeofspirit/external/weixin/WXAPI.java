package com.tribeofspirit.external.weixin;

import com.tribeofspirit.domain.model.FaceScanningResult;
import com.tribeofspirit.domain.model.User;

import java.io.File;
import java.util.Date;

/**
 * Author : gonwang
 * Create time : 20.10.2015.
 */
public interface WXAPI {

    File getImage(String mediaId);

    String getOpenId(String code);

    User getUserInfo(String openId);

    void sendResultMessage(String weChatOpenId, FaceScanningResult result);

    void sendEmbraceMessage(String weChatOpenId, User friend);

    void sendTreatMessage(String weChatOpenId, User friend);

    void sendAdmireMessage(String weChatOpenId, User friend);

    void sendEncourageMessage(String weChatOpenId, User friend);

    void sendTaskMessage(String weChatOpenId, User friend, String taskUrl);

    void sendRankingMessage(String weChatOpenId, Date date, int myPlace, int myScore, User champion, String rankingUrl);
}
