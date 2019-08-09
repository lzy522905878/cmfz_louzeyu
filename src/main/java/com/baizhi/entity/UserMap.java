package com.baizhi.entity;

import java.util.List;

public class UserMap {
    private String title;
    private List<UserCity> cities;

    @Override
    public String toString() {
        return "UserMap{" +
                "title='" + title + '\'' +
                ", cities=" + cities +
                '}';
    }

    public UserMap() {
    }

    public UserMap(String title, List<UserCity> cities) {
        this.title = title;
        this.cities = cities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserCity> getCities() {
        return cities;
    }

    public void setCities(List<UserCity> cities) {
        this.cities = cities;
    }
}
