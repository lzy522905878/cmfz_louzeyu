package com.baizhi.serviceimpl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> login(Admin admin, HttpSession session, String inputCode) {
        //获取验证码
        String imageCode = (String) session.getAttribute("code");
        Map<String, Object> map = new HashMap<>();
        //判断验证码
        if(imageCode.equals(inputCode)){
            Admin admin1 = adminDao.selectUsernameAndPassword(admin);
            //判断用户名
            if(admin.getUsername().equals(admin1.getUsername())){
                //判断密码
                if(admin.getPassword().equals(admin1.getPassword())){
                    session.setAttribute("admin",admin1);
                    map.put("success","200");
                    map.put("message","登陆成功");
                }else{
                    map.put("success","400");
                    map.put("message","密码错误");
                }
            }else{
                map.put("success","400");
                map.put("message","用户不存在");
            }
        }else{
            map.put("success","400");
            map.put("message","验证码错误");
        }
        return map;
    }
}
