package com.baizhi.serviceimpl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Chapter> showAll(Integer page, Integer rows, String album_id) {
        List<Chapter> chapters = chapterDao.selsctAllById((page - 1) * rows, rows, album_id);
        return chapters;
    }

    @Override
    public String add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setUp_date(new Date());
        chapterDao.insert(chapter);
        return chapter.getId();
    }

    @Override
    public Integer showCount() {
        Integer records = chapterDao.selectCount();
        return records;
    }

    @Override
    public void modify(Chapter chapter) {
        chapterDao.update(chapter);
    }

    @Override
    public List<Chapter> show() {
        List<Chapter> chapters = chapterDao.select();
        return chapters;
    }

    @Override
    public void delete(Chapter chapter) {
        chapterDao.delete(chapter.getId());
    }
}
