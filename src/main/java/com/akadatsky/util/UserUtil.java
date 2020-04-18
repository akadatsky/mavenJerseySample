package com.akadatsky.util;

import com.akadatsky.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

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

    public static String toJson(List<User> users) {
        return gson.toJson(users);
    }

    public static User addUser(User user) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        return user;
    }

    public static List<User> getUsers() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

}
