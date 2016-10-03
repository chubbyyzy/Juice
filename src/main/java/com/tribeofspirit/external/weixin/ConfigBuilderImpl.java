package com.tribeofspirit.external.weixin;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author : gonwang
 * Create time : 20.10.2015.
 */
@Component
public class ConfigBuilderImpl implements ConfigBuilder {

    public static final String[] jsApiList = {
            "checkJsApi",
            "onMenuShareTimeline",
            "onMenuShareAppMessage",
            "onMenuShareQQ",
            "onMenuShareWeibo",
            "hideMenuItems",
            "showMenuItems",
            "hideAllNonBaseMenuItem",
            "showAllNonBaseMenuItem",
            "translateVoice",
            "startRecord",
            "stopRecord",
            "onRecordEnd",
            "playVoice",
            "pauseVoice",
            "stopVoice",
            "uploadVoice",
            "downloadVoice",
            "chooseImage",
            "previewImage",
            "uploadImage",
            "downloadImage",
            "getNetworkType",
            "openLocation",
            "getLocation",
            "hideOptionMenu",
            "showOptionMenu",
            "closeWindow",
            "scanQRCode",
            "chooseWXPay",
            "openProductSpecificView",
            "addCard",
            "chooseCard",
            "openCard"
    };

    private WXContext wxContext;

    private SignatureGenerator signatureGenerator;

    private String appId;

    @Value("#{weixin['appId']}")
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Autowired
    public void setSignatureGenerator(SignatureGenerator signatureGenerator) {
        this.signatureGenerator = signatureGenerator;
    }

    @Autowired
    public void setWxContext(WXContext wxContext) {
        this.wxContext = wxContext;
    }

    public WXConfig buildFor(String url) {

        WXConfig wxConfig = new WXConfig();

        wxConfig.setDebug(true);

        String nonceStr = RandomStringUtils.random(16, true, true);
        wxConfig.setNonceStr(nonceStr);

        wxConfig.setAppId(appId);

        wxConfig.setTimestamp(System.currentTimeMillis());

        String ticket = wxContext.getJsApiTicket();

        String signature = signatureGenerator.generateSignature(ticket, wxConfig.getTimestamp(), wxConfig.getNonceStr(), url);

        wxConfig.setSignature(signature);

        wxConfig.setJsApiList(jsApiList);

        return wxConfig;
    }
}
