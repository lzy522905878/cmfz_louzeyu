package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class Banner {
    @Excel(name = "ID")
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "轮播图", type = 2, width = 30, height = 30)
    private String img_path;
    @Excel(name = "描述")
    private String description;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "上传时间", format = "yyyy-MM-dd")
    private Date up_date;

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", img_path='" + img_path + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", up_date=" + up_date +
                '}';
    }

    public Banner() {
    }

    public Banner(String id, String title, String img_path, String description, String status, Date up_date) {
        this.id = id;
        this.title = title;
        this.img_path = img_path;
        this.description = description;
        this.status = status;
        this.up_date = up_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }
}
