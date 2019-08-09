package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public interface AdminDao {
    Admin selectUsernameAndPassword(Admin admin);
}
