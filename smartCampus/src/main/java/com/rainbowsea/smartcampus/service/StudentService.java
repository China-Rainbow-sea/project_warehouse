package com.rainbowsea.smartcampus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbowsea.smartcampus.pojo.LoginForm;
import com.rainbowsea.smartcampus.pojo.Student;


public interface StudentService extends IService<Student> {

    Student login(LoginForm loginForm);

    Student getStudentById(Long userId);
}
