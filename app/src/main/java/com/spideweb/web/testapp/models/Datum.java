package com.spideweb.web.testapp.models;


import java.io.Serializable;

public class Datum implements Serializable {


    private int id;

    private int appId;

    private int position;

    private String name;

    private String thumbImage;

    private String appLink;

    private String packageName;

    private String fullThumbImage;

    private int splashActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFullThumbImage() {
        return fullThumbImage;
    }

    public void setFullThumbImage(String fullThumbImage) {
        this.fullThumbImage = fullThumbImage;
    }

    public int getSplashActive() {
        return splashActive;
    }

    public void setSplashActive(int splashActive) {
        this.splashActive = splashActive;
    }

}
