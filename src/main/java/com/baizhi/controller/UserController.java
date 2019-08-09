package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.entity.UserMap;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/showAll")
    public Map<String, Object> showAll(Integer page, Integer rows, HttpSession session) {
        Map<String, Object> map = userService.showAll(page, rows);
        session.setAttribute("users", map);
        return map;
    }

    @ResponseBody
    @RequestMapping("/edit")
    public void edit(User user, String oper) {
        if ("edit".equals(oper)) {
            userService.modify(user);
        }
    }

    @ResponseBody
    @RequestMapping("/upload")
    public void upload(MultipartFile pic_img, HttpSession session, String id) {
        if (!(pic_img == null)) {
            String realPath = session.getServletContext().getRealPath("/user/pic_img");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String filename = pic_img.getOriginalFilename();
            String newPic = new Date().getTime() + "-" + filename;
            try {
                pic_img.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            User user = new User();
            user.setId(id);
            user.setPic_img(newPic);
            userService.modify(user);
        }
    }

    @ResponseBody
    @RequestMapping("/status")
    public void status(User user) {
        user = userService.queryOne(user);
        if ("1".equals(user.getStatus())) {
            user.setStatus("0");
        } else {
            user.setStatus("1");
        }
        userService.modify(user);
    }

    @ResponseBody
    @RequestMapping("/export")
    public String userExport(HttpSession session, String mark) {
        List<User> users;
        if (!mark.equals("1")) {
            Map<String, Object> map = (Map<String, Object>) session.getAttribute("users");
            users = (List<User>) map.get("rows");
        } else {
            users = userService.queryAll();
        }
        //获取路径
        String realPath = session.getServletContext().getRealPath("/user/pic_img");
        for (User user : users) {
            String pic_img = user.getPic_img();
            user.setPic_img(realPath + "/" + pic_img);
        }
        //导出
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息汇总表", "用户信息"), User.class, users);
        try {
            workbook.write(new FileOutputStream(new File("/MacOS/用户/macoslouzeyu/桌面")));
            workbook.close();
            return "信息导出成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "信息导出失败";
        }
    }

    @RequestMapping("/showByMonth")
    @ResponseBody
    public Map<String, Object> showByMonth() {
        Map<String, Object> map = userService.selectByMonth();
        System.out.println(map);
        return map;
    }

    @RequestMapping("/showByCity")
    @ResponseBody
    public List<UserMap> showByCity() {
        List<UserMap> userMaps = userService.selectByCity();
        return userMaps;
    }
}
