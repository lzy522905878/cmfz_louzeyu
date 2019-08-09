package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/getImageCode")
    public void getImageCode(HttpSession session, HttpServletResponse response) throws Exception {
        String securityCode = ImageCodeUtil.getSecurityCode();
        session.setAttribute("code", securityCode);
        BufferedImage image = ImageCodeUtil.createImage(securityCode);
        ImageIO.write(image, "png", response.getOutputStream());
    }

    @ResponseBody
    @RequestMapping("/login")
    public Map<String, Object> login(Admin admin, HttpSession session, String inputCode) {
        Map<String, Object> map = adminService.login(admin,session,inputCode);
        return map;
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }
}
