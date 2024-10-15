package com.rainbowsea.smartcampus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbowsea.smartcampus.mapper.GradeMapper;
import com.rainbowsea.smartcampus.pojo.Grade;
import com.rainbowsea.smartcampus.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("gradeService")
@Transactional  // 事务注解
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {


}
