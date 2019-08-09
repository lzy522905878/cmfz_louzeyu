package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChapterDao extends BaseDao<Chapter> {
    List<Chapter> selsctAllById(@Param("start") Integer page, @Param("rows") Integer rows, @Param("album_id") String album_id);

    List<Chapter> select();
}
