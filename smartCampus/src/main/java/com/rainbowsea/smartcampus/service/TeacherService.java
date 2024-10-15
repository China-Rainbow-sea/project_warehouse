package com.rainbowsea.smartcampus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbowsea.smartcampus.pojo.LoginForm;
import com.rainbowsea.smartcampus.pojo.Teacher;


public interface TeacherService extends IService<Teacher> {


    Teacher login(LoginForm loginForm);

    Teacher getTeacherById(Long userId);
}
