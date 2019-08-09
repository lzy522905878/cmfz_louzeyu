package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.springframework.stereotype.Component;

@Component
public interface ArticleDao extends BaseDao<Article> {
    Article selectOne(Article article);
}
