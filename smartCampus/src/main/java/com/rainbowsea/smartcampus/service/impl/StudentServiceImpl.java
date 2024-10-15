package com.rainbowsea.smartcampus.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbowsea.smartcampus.mapper.StudentMapper;
import com.rainbowsea.smartcampus.pojo.Admin;
import com.rainbowsea.smartcampus.pojo.LoginForm;
import com.rainbowsea.smartcampus.pojo.Student;
import com.rainbowsea.smartcampus.pojo.Teacher;
import com.rainbowsea.smartcampus.service.StudentService;
import com.rainbowsea.smartcampus.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("stuService")
@Transactional  // 事务注解
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {


    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));

        Student student = baseMapper.selectOne(queryWrapper);


        return student;
    }

    @Override
    public Student getStudentById(Long userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return baseMapper.selectOne(queryWrapper);
    }
}
