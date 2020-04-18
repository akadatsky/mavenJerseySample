package com.akadatsky.util;

import com.akadatsky.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

public class UserUtil {

    private static Gson gson = new Gson();
    private static XmlMapper xmlMapper = new XmlMapper();

    public static String toJson(User user) {
        return gson.toJson(user);
    }

    public static User fromJson(String json) {
        return gson.fromJson(json, User.class);
    }

    public static String toXml(User user) {
        try {
            return xmlMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static User fromXml(String xml) {
        try {
            return xmlMapper.readValue(xml, User.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
