package com.rainbowsea.smartcampus.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbowsea.smartcampus.mapper.ClazzMapper;
import com.rainbowsea.smartcampus.pojo.Clazz;
import com.rainbowsea.smartcampus.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("clazzService")
@Transactional  // 事务注解
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

}
