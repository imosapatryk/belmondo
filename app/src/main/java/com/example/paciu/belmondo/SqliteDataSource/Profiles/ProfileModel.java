package com.example.paciu.belmondo.SqliteDataSource.Profiles;
import com.example.paciu.belmondo.Profile.Sex;

public class ProfileModel {

    private int id;
    private String name;
    private int weight;
    private int height;
    private Sex sex;
    private String birthDate;

    public ProfileModel() {
        name = "";
    }

    public ProfileModel(String name, int weight, int height, String birthDate, Sex sex) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public ProfileModel(int id, String name, int weight, int height, String birthDate, Sex sex) {
        this(name, weight, height, birthDate, sex);
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
