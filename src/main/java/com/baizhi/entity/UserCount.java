package com.baizhi.entity;

public class UserCount {
    private String sex;
    private String months;
    private Integer count;

    @Override
    public String toString() {
        return "UserCount{" +
                "sex='" + sex + '\'' +
                ", months='" + months + '\'' +
                ", count=" + count +
                '}';
    }

    public UserCount() {
    }

    public UserCount(String sex, String months, Integer count) {
        this.sex = sex;
        this.months = months;
        this.count = count;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
