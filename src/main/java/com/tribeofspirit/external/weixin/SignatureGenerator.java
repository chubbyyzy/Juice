package com.tribeofspirit.external.weixin;

/**
 * Author : gonwang
 * Create time : 20.10.2015.
 */
public interface SignatureGenerator {

    String generateSignature(String jsApiTicket, long timestamp, String nonce, String url);
}
