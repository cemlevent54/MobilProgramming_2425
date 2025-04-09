package com.example.week4_app2.data;

public class Person {
    private String name;
    private int age;
    private int imageResId;
    private boolean gender;

    public Person(String name, int age, int imageResId, boolean gender) {
        this.name = name;
        this.age = age;
        this.imageResId = imageResId;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setGender(boolean gen) {
        this.gender = gen;
    }
}

