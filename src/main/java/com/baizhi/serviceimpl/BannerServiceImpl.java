package com.baizhi.serviceimpl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Banner> showAll(Integer page, Integer rows) {
        List<Banner> banners = bannerDao.selectAll(page, rows);
        return banners;
    }

    @Override
    public String add(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        banner.setStatus("0");
        banner.setUp_date(new Date());
        bannerDao.insert(banner);
        return banner.getId();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer count() {
        Integer count = bannerDao.count();
        return count;
    }

    @Override
    public void modify(Banner banner) {
        bannerDao.update(banner);
    }

    @Override
    public void remove(Banner banner) {
        bannerDao.delete(banner.getId());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Banner queryOne(Banner banner) {
        banner = bannerDao.selectOne(banner.getId());
        if (banner.getStatus().equals("0")) {
            banner.setStatus("1");
        } else {
            banner.setStatus("0");
        }
        return banner;
    }
}
