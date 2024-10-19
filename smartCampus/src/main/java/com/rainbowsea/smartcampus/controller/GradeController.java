package com.rainbowsea.smartcampus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbowsea.smartcampus.pojo.Grade;
import com.rainbowsea.smartcampus.service.GradeService;
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


@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Resource
    private GradeService gradeService;


    @ApiOperation("查询年级信息,分页带条件")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(
            @ApiParam("分页查询页码数") @PathVariable("pageNo") Integer pageNo, // 页码数
            @ApiParam("分页查询页大小") @PathVariable("pageSize") Integer pageSize,  // 页大小
            @ApiParam("分页查询模糊匹配的名称") String gradeName
    ) {
        // 分页 带条件查询
        Page<Grade> page = new Page<>();
        // 通过服务层
        IPage<Grade> pageRs = gradeService.getGradeByOpr(page, gradeName);
        // 封装Result对象并返回
        return Result.ok(pageRs);


    }


    @ApiOperation("添加或者修改年级信息")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(
            @ApiParam("JSON的grade对象转换后台数据模型,有id 属性是修改，没有则是增加") @RequestBody Grade grade // RequestBody
            // 接收前端提交的是JSON格式的字符串，封装到对应的
            // java
            // bean对象当中去

    ) {
        // 接收参数
        // 调用服务层方法完成增减或者修改,实际本质调用的是 mybatis-plus 内置的方法
        gradeService.saveOrUpdate(grade);

        return Result.ok();

    }


    @ApiOperation("删除Grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(
            @ApiParam("JSON的年级id集合,映射为后台List<Integer>，要删除的所以的grade的id的json集合") @RequestBody List<Integer> ids  //
            // RequestBody 前端
            // JSON格式字符串转换为对应的Java bean对象
    ) {
        gradeService.removeByIds(ids);
        return Result.ok();

    }


    @ApiOperation("获取所有Grade信息")
    @GetMapping("/getGrades")
    public Result getGrades(
    ) {
        List<Grade> grades = gradeService.getGrades();
        return Result.ok();
    }

}
