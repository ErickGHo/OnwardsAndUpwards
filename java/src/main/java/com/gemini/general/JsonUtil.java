package com.gemini.general;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class JsonUtil {


    private static ObjectMapper jsonMapperInstance;

    private static ObjectMapper getJsonMapperInstance() {
        if (jsonMapperInstance == null)
            jsonMapperInstance = new ObjectMapper();
        return jsonMapperInstance;
    }

    public static <T> T readValue(String url, Class<T> clazz) {
        try {
            return getJsonMapperInstance().readValue(new URL(url), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJsonString(Object o) {
        try {
            return getJsonMapperInstance().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
