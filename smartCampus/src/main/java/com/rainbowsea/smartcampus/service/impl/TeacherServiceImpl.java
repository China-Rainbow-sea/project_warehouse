package com.rainbowsea.smartcampus.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbowsea.smartcampus.mapper.TeacherMapper;
import com.rainbowsea.smartcampus.pojo.LoginForm;
import com.rainbowsea.smartcampus.pojo.Student;
import com.rainbowsea.smartcampus.pojo.Teacher;
import com.rainbowsea.smartcampus.service.TeacherService;
import com.rainbowsea.smartcampus.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("teaService")
@Transactional  // 事务注解
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Override
    public Teacher login(LoginForm loginForm) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));

        Teacher teacher = baseMapper.selectOne(queryWrapper);


        return teacher;
    }

    @Override
    public Teacher getTeacherById(Long userId) {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return baseMapper.selectOne(queryWrapper);
    }
}
