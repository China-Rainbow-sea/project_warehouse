package com.rainbowsea.serivce.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbowsea.bean.Furn;
import com.rainbowsea.mapper.FurnMapper;
import com.rainbowsea.serivce.FurnService;
import org.springframework.stereotype.Service;


@Service
public class FurnServiceImpl
extends ServiceImpl<FurnMapper, Furn>
implements FurnService {
}
