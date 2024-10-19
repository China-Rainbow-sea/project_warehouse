package com.rainbowsea.smartcampus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbowsea.smartcampus.mapper.GradeMapper;
import com.rainbowsea.smartcampus.pojo.Grade;
import com.rainbowsea.smartcampus.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service("gradeService")
@Transactional  // 事务注解
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {


    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> pageParam, String gradeName) {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();

        // 设置查询条件
        if(!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like("name",gradeName);
        }

        // 设置排序规则
        queryWrapper.orderByDesc("id"); // 根据 id 进行排序

        // 分页查询数据
        Page<Grade> page = baseMapper.selectPage(pageParam, queryWrapper);

        return page;
    }

    @Override
    public List<Grade> getGrades() {

        return Collections.emptyList();
    }
}
