package com.example.paciu.belmondo.SqliteDataSource.Activities;

/**
 * Created by paciu on 10.05.2016.
 */
public class SportActivityModel {
    private int id;
    private int type;
    private String name;
    private float MET;
    private float upperValueInMPerSecond;

    public SportActivityModel(){}

    public SportActivityModel(int id, int type, String name, float MET, float upperValueInMPerSecond) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.MET = MET;
        this.upperValueInMPerSecond = upperValueInMPerSecond;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMET() {
        return MET;
    }

    public void setMET(float MET) {
        this.MET = MET;
    }

    public float getUpperValueInMPerSecond() {
        return upperValueInMPerSecond;
    }

    public void setUpperValueInMPerSecond(float upperValueInMPerSecond) {
        this.upperValueInMPerSecond = upperValueInMPerSecond;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
