package com.example.mylab.model;

public class Country {
    private String name; // Название страны
    private String code; // Телефонный код

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}