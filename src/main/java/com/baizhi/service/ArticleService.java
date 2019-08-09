package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    Map<String,Object> showAll(Integer page, Integer rows);
    void remove(Article article);
    void modify(Article article);
    Article showOne(Article article);
    String add(Article article);
}
