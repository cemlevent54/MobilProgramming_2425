package com.example.week5_app1.data;

public class Person {
    private int id;
    private String name;
    private int age;
    private int imageResId;
    private boolean gender;

    public Person(int id, String name, int age, int imageResId, boolean gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.imageResId = imageResId;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

