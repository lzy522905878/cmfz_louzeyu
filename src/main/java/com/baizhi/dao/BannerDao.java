package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BannerDao {
    List<Banner> selectAll(@Param("page") Integer page, @Param("rows") Integer rows);
    Integer count();
    void insert(Banner banner);
    void update(Banner banner);
    void delete(String id);
    Banner selectOne(String id);
}
