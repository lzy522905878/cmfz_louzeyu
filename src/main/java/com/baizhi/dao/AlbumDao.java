package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AlbumDao extends BaseDao<Album> {
    List<Album> select();
}
