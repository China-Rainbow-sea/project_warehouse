package com.rainbowsea.smartcampus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbowsea.smartcampus.pojo.Admin;
import com.rainbowsea.smartcampus.pojo.LoginForm;

public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    Admin getAdminById(Long userId);
}
