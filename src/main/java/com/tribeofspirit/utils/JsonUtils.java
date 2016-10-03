package com.tribeofspirit.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author : gonwang
 * Create time : 2015/11/22.
 */
public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String writeAsString(Object object) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert as json string.", e);
            return "";
        }
    }

}
