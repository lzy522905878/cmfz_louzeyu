package com.baizhi.serviceimpl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> showAll(Integer page, Integer rows) {
        List<Album> albums = albumDao.selectAll((page - 1) * rows, rows);
        List<Chapter> chaps = chapterDao.select();
        Map<String, Integer> map = new HashMap<>();
        for (Album album : albums) {
            Integer count = 0;
            for (Chapter chap : chaps) {
                if (album.getId().equals(chap.getAlbum_id())) {
                    count++;
                }
                map.put(album.getId(), count);
            }
        }
        for (Album album : albums) {
            Integer number = map.get(album.getId());
            album.setNumber(number);
        }
        return albums;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer showCount() {
        Integer records = albumDao.selectCount();
        return records;
    }

    @Override
    public String add(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setPub_date(new Date());
        album.setNumber(0);
        /*album.setScore(5.0);*/
        albumDao.insert(album);
        return album.getId();
    }

    @Override
    public void modify(Album album) {
        albumDao.update(album);
    }

    @Override
    public void remove(Album album) {
        albumDao.delete(album.getId());
    }

    @Override
    public List<Album> show() {
        List<Album> albums = albumDao.select();
        return albums;
    }
}
