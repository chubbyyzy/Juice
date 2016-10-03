package com.tribeofspirit.web.interceptor;

import com.tribeofspirit.external.weixin.WXAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * Author : gonwang
 * Create time : 2015/11/15.
 */

public class WeixinInterceptor extends HandlerInterceptorAdapter {

    private String weiXinOpenIdOAuthUrl;

    private WXAPI wxapi;

    private String appId;

    @Autowired
    public void setWxapi(WXAPI wxapi) {
        this.wxapi = wxapi;
    }

    @Value("#{weixin['openIdOAuthUrl']}")
    public void setWeiXinOpenIdOAuthUrl(String weiXinOpenIdOAuthUrl) {
        this.weiXinOpenIdOAuthUrl = weiXinOpenIdOAuthUrl;
    }

    @Value("#{weixin['appId']}")
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute(WeiXinOpenId.ATTRIBUTE_NAME_IN_SESSION) != null) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        boolean requireWeiXinId = isRequireWeiXinId(handlerMethod);

        if (!requireWeiXinId) return true;

        String code = request.getParameter("code");

        if (code != null) {
            String weiXinOpenId = wxapi.getOpenId(code);
            request.getSession().setAttribute(WeiXinOpenId.ATTRIBUTE_NAME_IN_SESSION, weiXinOpenId);
            return true;
        }

        String openIdOAuthUrl = buildWeiXinOAuthUrl(request);
        response.sendRedirect(openIdOAuthUrl);

        return false;
    }

    private boolean isRequireWeiXinId(HandlerMethod handlerMethod) {

        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();

        for (MethodParameter methodParameter : methodParameters) {
            if (methodParameter.hasParameterAnnotation(WeiXinOpenId.class)) {
                return true;
            }
        }

        return false;
    }

    private String buildWeiXinOAuthUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        return MessageFormat.format(weiXinOpenIdOAuthUrl, appId, URLEncoder.encode(request.getRequestURL().toString(), "UTF-8"));
    }
}
