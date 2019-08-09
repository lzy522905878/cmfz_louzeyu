package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {
    List<Chapter> showAll(Integer page, Integer rows, String album_id);
    String add(Chapter chapter);
    Integer showCount();
    void modify(Chapter chapter);
    List<Chapter> show();
    void delete(Chapter chapter);
}
