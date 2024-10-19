package com.rainbowsea.smartcampus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbowsea.smartcampus.pojo.Clazz;
import com.rainbowsea.smartcampus.service.ClazzService;
import com.rainbowsea.smartcampus.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "班级控制器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    @ApiOperation("查询班级信息,分页带条件")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzByOpr(
            @ApiParam("分页查询的页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页查询页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("分页查询的查询条件") Clazz clazz
    ) {

        Page<Clazz> page = new Page<>(pageNo, pageSize);

        IPage<Clazz> iPage = clazzService.getClazzsByOpr(page, clazz);
        return Result.ok(iPage);
    }


    @ApiOperation("保存或者修改班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(
            @ApiParam("JSON转换后端Clazz数据模型") @RequestBody Clazz clazz
    ) {
        return Result.ok();
    }


    @ApiOperation("删除单个和多个班级")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(
            @ApiParam("多个班级id的JSON") @RequestBody List<Integer> ids
    ) {

        clazzService.removeByIds(ids);

        return Result.ok();

    }

    @ApiOperation("查询所有班级信息")
    @GetMapping("/getClazzs")
    public Result getClazz() {
        List<Clazz> clazzes = clazzService.getClazz();
        return Result.ok(clazzes);
    }
}
