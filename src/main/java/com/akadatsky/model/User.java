package com.akadatsky.model;

public class User {

    private String name;
    private int age;

    public User() {
    }

    public User(String firstName, int age) {
        this.name = firstName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
