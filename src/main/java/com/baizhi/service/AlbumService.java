package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    List<Album> showAll(Integer page, Integer rows);
    Integer showCount();
    String add(Album album);
    void modify(Album album);
    void remove(Album album);
    List<Album> show();
}
