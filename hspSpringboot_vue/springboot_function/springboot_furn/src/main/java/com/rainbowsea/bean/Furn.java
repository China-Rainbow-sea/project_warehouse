package com.rainbowsea.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j

// 如果 Furn 类名和表 furl 名字不能对应，可以通过 @TableName 注解指明对应映射的 数据表明即可。
// 如果对应上了，可以不用写
@TableName("furn")  // 设置数据表与java bean 类映射对应的别名，
public class Furn {  //
    // 这里我们使用@TableId,表主键标识
    // 当我们再 private Integer id 上标识了 @TableId
    @TableId(type = IdType.AUTO)  // 主键的自增策略，需要注意的是:我们需要在数据库当中也是设置了主键自增才行
    private Integer id;
    // 如果是对String 进行非空校验，我们应该使用@NotEmpty
    @NotEmpty(message = "请输入家居名")
    private String name;
    @NotEmpty(message = "请输入厂商名")
    private String maker;
    @NotNull(message = "请输入数字")
    @Range(min = 0, max = 100000, message = "价格不能小于0")
    private Double price;
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "销量不能小于0")
    private Integer sales;
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "库存不能小于0")
    private Integer stock;
}
