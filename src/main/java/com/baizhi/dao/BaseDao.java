package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {
    List<T> selectAll(@Param("start") Integer page, @Param("rows") Integer rows);

    Integer selectCount();

    void insert(T t);

    void update(T t);

    void delete(String id);
}
