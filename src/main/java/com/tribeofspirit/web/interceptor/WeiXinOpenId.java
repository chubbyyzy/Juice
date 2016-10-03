package com.tribeofspirit.web.interceptor;

import java.lang.annotation.*;

/**
 * Author : gonwang
 * Create time : 2015/11/15.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface WeiXinOpenId {

    public static final String ATTRIBUTE_NAME_IN_SESSION = "_weixin_open_id";
}
