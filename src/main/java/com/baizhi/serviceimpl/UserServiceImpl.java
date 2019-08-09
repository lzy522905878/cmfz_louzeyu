package com.baizhi.serviceimpl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import com.baizhi.entity.UserMap;
import com.baizhi.service.UserService;
import com.baizhi.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userDao.selectAll((page - 1) * rows, rows);
        Integer count = userDao.selectCount();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("page", page);
        map.put("records", count);
        map.put("total", total);
        map.put("rows", users);
        return map;
    }

    @Override
    public void modify(User user) {
        userDao.update(user);
    }

    @Override
    public User queryOne(User user) {
        user = userDao.selectOne(user.getId());
        return user;
    }

    @Override
    public List<User> queryAll() {
        return userDao.select();
    }

    @Override
    public Map<String, Object> selectByMonth() {
        HashMap<String, Object> map = new HashMap<>();
        //存放月份
        HashSet<Object> set = new HashSet<>();
        //获取所有数据
        List<UserCount> total = userDao.total();
        //性别
        ArrayList<String> sex = new ArrayList<>();
        sex.add("boys");
        sex.add("girls");
        //女生
        ArrayList<UserCount> girls = new ArrayList<>();
        //男生
        ArrayList<UserCount> boys = new ArrayList<>();
        for (UserCount userCount : total) {
            set.add(userCount.getMonths());
            if (userCount.getSex().equals("女")) {
                girls.add(userCount);
            } else {
                boys.add(userCount);
            }
        }
        Map<String, Integer> girl = new HashMap<>();
        for (Object month : set) {
            girl.put((String) month, 0);
            for (UserCount userCount : girls) {
                if (userCount.getMonths().equals(month)) {
                    girl.put((String) month, userCount.getCount());
                }
            }
        }
        Map<String, Integer> boy = new HashMap<>();
        for (Object month : set) {
            boy.put((String) month, 0);
            for (UserCount userCount : boys) {
                if (month.equals(userCount.getMonths())) {
                    boy.put((String) month, userCount.getCount());
                }
            }
        }
        Collection<Integer> nv = girl.values();
        Collection<Integer> nan = boy.values();
        map.put("month", set);
        map.put("sex", sex);
        map.put("girls", nv);
        map.put("boys", nan);
        return map;
    }

    @Override
    public List<UserMap> selectByCity() {
        //查询所有数据
        List<UserMap> userMaps = userDao.map();
        return userMaps;
    }

    @Override
    public String add(User user) {
        user.setId(UUID.randomUUID().toString());
        String md5Code = Md5Utils.getMd5Code(user.getPassword());
        user.setPassword(md5Code);
        user.setStatus("0");
        user.setReg_date(new Date());
        user.setSalt(Md5Utils.getSalt(8));
        userDao.insert(user);
        return user.getId();
    }

}
