package com.example.paciu.belmondo.DataSource.Profiles;

import java.util.Date;

/**
 * Created by paciu on 04.03.2016.
 */
public class Profile {

    private int id;
    private String name;
    private float weight;
    private float height;
    private String birthDate;

    public Profile() {
        name = "";
    }

    public Profile(String name, float weight, float height, String birthDate) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.birthDate = birthDate;
    }

    public Profile(int id, String name, float weight, float height, String birthDate) {
        this(name, weight, height, birthDate);
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
