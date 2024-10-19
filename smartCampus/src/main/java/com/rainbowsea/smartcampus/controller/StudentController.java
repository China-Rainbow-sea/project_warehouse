package com.rainbowsea.smartcampus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbowsea.smartcampus.pojo.Student;
import com.rainbowsea.smartcampus.service.StudentService;
import com.rainbowsea.smartcampus.util.MD5;
import com.rainbowsea.smartcampus.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {
    @Resource
    private StudentService studentService;


    @ApiOperation("分页条件查询学生信息")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNO,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询的条件") Student student
    ) {
        // 分页信息封装Page对象
        Page<Student> pageParam = new Page<>(pageNO, pageSize);

        IPage<Student> studentIPage = studentService.getStudentByOpr(pageParam, student);
        // 封装result返回
        return Result.ok(studentIPage);
    }


    @ApiOperation("保存/或者修改/增加学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(
            @ApiParam("要保存或修改的学生的json") @RequestBody Student student  // RequestBody 将前端的json格式的字符串转换为后端对应的java bean对象
    ) {

        Integer id = student.getId();
        if (null == id || 0 == id || (!Strings.isEmpty(student.getPassword()))) {
            //对学生的密码进行加密
            student.setPassword(MD5.encrypt(student.getPassword()));
        }

        //保存学生信息进入数据库
        studentService.saveOrUpdate(student);

        return Result.ok();
    }
}
