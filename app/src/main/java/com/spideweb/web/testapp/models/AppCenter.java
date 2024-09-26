package com.spideweb.web.testapp.models;

import java.io.Serializable;
import java.util.List;

public class AppCenter implements Serializable {
    private Integer id;

    private int position;

    private String name;

    private Integer isActive;

    private String catgeory;
 
    private List<SubCategory> subCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getCatgeory() {
        return catgeory;
    }

    public void setCatgeory(String catgeory) {
        this.catgeory = catgeory;
    }

    public List<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }
    

}
