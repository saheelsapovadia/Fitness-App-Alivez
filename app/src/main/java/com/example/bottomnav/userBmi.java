package com.example.bottomnav;

public class userBmi {
    int value;
    int weight;
    int height;
    int age;
    float fat_percentage;
    String gender;
    public userBmi() {
        //no-arg
    }

    public userBmi(int value, int weight, int height, int age, float fat_percentage, String gender) {
        this.value = value;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.fat_percentage = fat_percentage;
        this.gender = gender;
    }

    public float getFat_percentage() {
        return fat_percentage;
    }

    public void setFat_percentage(float fat_percentage) {
        this.fat_percentage = fat_percentage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
