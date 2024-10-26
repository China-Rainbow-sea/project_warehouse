package com.rainbowsea.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbowsea.bean.Furn;
import org.apache.ibatis.annotations.Mapper;


/**
 * 如果是 mybatis-plus 我们Mapper接口可以通过 extends ，接BaseMapper
 * 扩张功能。
 */
@Mapper // 或者是在场景启动器当中添加上: 注解。 @MapperScan(basePackages = "com.rainbowsea.mapper")
public interface FurnMapper extends BaseMapper<Furn> {

    // 如果你有其它的方法，可以再该接口声明
    // 并在对应的Mapper.xml 进行配置实现
}
