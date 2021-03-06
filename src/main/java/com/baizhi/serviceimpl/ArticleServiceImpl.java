package com.baizhi.serviceimpl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Article> articles = articleDao.selectAll((page - 1) * rows, rows);
        Integer reocrds = articleDao.selectCount();
        Integer total = reocrds % rows == 0 ? reocrds / rows : reocrds / rows + 1;
        map.put("page", page);
        map.put("reocrds", reocrds);
        map.put("total", total);
        map.put("rows", articles);
        return map;
    }

    @Override
    public void remove(Article article) {
        articleDao.delete(article.getId());
    }

    @Override
    public void modify(Article article) {
        articleDao.update(article);
    }

    @Override
    public Article showOne(Article article) {
        return articleDao.selectOne(article);
    }

    @Override
    public String add(Article article) {
        article.setId(UUID.randomUUID().toString());
        //设置状态
        article.setStatus("0");
        //设置上传日期
        article.setUp_date(new Date());
        article.setUser_id("公开");
        System.out.println(article);
        articleDao.insert(article);
        return article.getId();
    }
}
