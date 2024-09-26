package com.spideweb.web.testapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NativeAdd implements Serializable {


    private Integer id;

    private Integer position;

    private String image;

    private String playstoreLink;

    private String packageName;

    private Integer isActive;

    private Integer imageActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlaystoreLink() {
        return playstoreLink;
    }

    public void setPlaystoreLink(String playstoreLink) {
        this.playstoreLink = playstoreLink;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getImageActive() {
        return imageActive;
    }

    public void setImageActive(Integer imageActive) {
        this.imageActive = imageActive;
    }

}