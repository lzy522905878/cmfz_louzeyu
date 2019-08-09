package com.baizhi.entity;

import java.util.Date;

public class Article {
    private String id;
    private String title;
    private String insert_img;
    private String content;
    private Date up_date;
    private String user_id;
    private String status;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", insert_img='" + insert_img + '\'' +
                ", content='" + content + '\'' +
                ", up_date=" + up_date +
                ", user_id='" + user_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Article() {
    }

    public Article(String id, String title, String insert_img, String content, Date up_date, String user_id, String status) {
        this.id = id;
        this.title = title;
        this.insert_img = insert_img;
        this.content = content;
        this.up_date = up_date;
        this.user_id = user_id;
        this.status = status;
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

    public String getInsert_img() {
        return insert_img;
    }

    public void setInsert_img(String insert_img) {
        this.insert_img = insert_img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
