package com.tribeofspirit.external.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Author : gonwang
 * Create time : 20.10.2015.
 */
@Component
public class SignatureGeneratorImpl implements SignatureGenerator {

    public static final String JSAPI_TICKET_PARAM_NAME = "jsapi_ticket";

    public static final String TIMESTAMP_PARAM_NAME = "timestamp";

    public static final String NONCESTR_PARAM_NAME = "noncestr";

    public static final String URL_PARAM_NAME = "url";

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String generateSignature(String jsApiTicket, long timestamp, String nonce, String url) {

        String content = preparePlainSignature(jsApiTicket, timestamp, nonce, url);

        return digest(content);
    }

    private String digest(String content) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] digest = md.digest(content.getBytes());

            return byteToStr(digest);

        } catch (NoSuchAlgorithmException e) {

            logger.error("Not found algorithm SHA-1.", e);

            return null;
        }
    }

    private String preparePlainSignature(String jsApiTicket, long timestamp, String nonce, String url) {

        String[] paramArr =
                new String[] {
                        JSAPI_TICKET_PARAM_NAME + "=" + jsApiTicket,
                        TIMESTAMP_PARAM_NAME + "=" + timestamp,
                        NONCESTR_PARAM_NAME + "=" + nonce,
                        URL_PARAM_NAME + "=" + url };

        Arrays.sort(paramArr);

        return paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2]).concat("&" + paramArr[3]);
    }

    private String byteToStr(byte[] byteArray) {

        String strDigest = "";

        for (byte aByteArray : byteArray) {
            strDigest += byteToHexStr(aByteArray);
        }

        return strDigest;
    }

    private String byteToHexStr(byte mByte) {

        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };

        char[] tempArr = new char[2];

        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        return new String(tempArr);
    }
}
