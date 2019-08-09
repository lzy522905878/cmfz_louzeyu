package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    List<Banner> showAll(Integer page, Integer rows);
    String add(Banner banner);
    Integer count();
    void modify(Banner banner);
    void remove(Banner banner);
    Banner queryOne(Banner banner);
}
