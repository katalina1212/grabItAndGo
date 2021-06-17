package com.example.grabitandgo;

public class CategoriesActivity {

    private String name;
    private String image;

    public CategoriesActivity(String name, String image) {
        this.name = name;
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}