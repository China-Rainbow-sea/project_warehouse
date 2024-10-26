package com.rainbowsea.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbowsea.bean.Furn;
import com.rainbowsea.serivce.FurnService;
import com.rainbowsea.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


/**
 * 说明:
 * 1. 因为当前我们的项目是前后端分离的，在默认程序下，前端发出请求
 * 2. 后端接受请求，但是需要注意的是，我们这里设置了，前端发送的数据是 json 格式的字符串
 * 3. 同时我们后端发送的数据也是 json 格式的字符串格式的数据给前端
 * 为了方便，我们就在类上使用 @RestController 注解
 */


@RestController
@Slf4j
public class FurnController {

    // 装配上 Service
    @Resource
    private FurnService furnService;


    /**
     * 说明:
     * 1.我们的前端如果是以 json 格式的字符串发送添加信息的Furn数据，那么我们后端
     * 需要将json格式的数据转换为我们 java bean 对应上的 bean对象才能封装到对应的 bean 当中
     *
     * @param furn
     * @return
     * @RequestBody 注解将前端提交的 json 格式的数据转换为 后端 java bean 数据
     * 2 如果前端是以表单格式提交了，则不需要使用 @RequestBody 注解
     * http 的请求头的 content-type 是对应的
     */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Furn furn, Errors errors) {
        // 如果出现校验错误，springboot 底层会把错误信息，封装到errors当中
        // @Validated 用于 JSR 303 校验
        // @RequestBody  将客户端提交的json数据，封装成javaBean对象，再以javebean以json对象形式返回
       /* log.info("furn={}", furn);
        furnService.save(furn);
        return Result.success(); // 返回成功信息*/

        /*HashMap<String, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();

        // 遍历 将错误信息放入到map,当然可能有，也可能没有错误
        //iter
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // map 为空说明没有错误提示，校验成功，则可以保存到数据库当中
        if (map.isEmpty()) {
            log.info("furn={}", furn);
            furnService.save(furn);
            return Result.success(); // 返回成功信息

        } else {
            return Result.error("400", "后端校验失败", map);
        }*/

        //如果出现校验错误, sboot 底层会把错误信息，封装到errors

        //定义map ,准备把errors中的校验错误放入到map,如果有错误信息
        //就不真正添加，并且将错误信息通过map返回给客户端-客户端就可以取出显示
        HashMap<String, Object> map = new HashMap<>();

        List<FieldError> fieldErrors = errors.getFieldErrors();
        //遍历 将错误信息放入到map , 当然可能有，也可能没有错误
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        if (map.isEmpty()) { //说明没有校验错误,正常添加
            log.info("furn={}", furn);
            furnService.save(furn);
            return Result.success(); //返回成功信息
        } else {
            return Result.error("400", "后端校验失败~", map);
        }
    }

    /**
     * 返回所有的家居信息，后面再考虑分页显示
     *
     * @return
     */
    @RequestMapping("/furns")
    public Result listFurns() {
        List<Furn> furns = furnService.list();
        return Result.success(furns);
    }


    /**
     * 说明
     *
     * @param furn
     * @return
     * @PutMapping 我们使用 Rest风格，因为这里是修改的请求，使用put请求
     * @RequestBody 表示前端/客户端 发送的数据是以 json 格式来发送，通过@
     */
    @PutMapping("/update")
    public Result update(@RequestBody Furn furn) {
        furnService.updateById(furn);
        return Result.success();

    }


    /*
    处理删除
    使用 url 占位符+@PathVariable 配合使用 ，@PathVariable 表示前端提交数据必须要有该参数，
    否则提交失败
    使用 rest 风格 ->del方式
     */
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Integer id) {
        // removeById 用的是 Mybatis-plus 提供的方法
        furnService.removeById(id);
        return Result.success();
    }

    /*
    增加方法[接口]，根据id,返回对应的家居信息
    如何设计？依然使用 url 占位符+@PathVariable
     */
    @GetMapping("/find/{id}")
    public Result findById(@PathVariable Integer id) {
        Furn furn = furnService.getById(id);
        log.info("furn={}", furn);
        return Result.success(furn); // 返回成功的信息-携带查询到 furn 信息
    }

    // 分页查询的接口/方法
    // 我们讲解原生 java web 时，讲过：分页模型->可以回顾，底层机制类似

    /**
     * @param pageNum  显示第几页 ，默认是 1
     * @param pageSize 每页显示几条记录，默认是 5
     * @return
     */
    @GetMapping("/furnsByPage")
    public Result listFurnsByPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        // 这里通过 page 方法，返回 page 对象，对象中就封装了分页数据
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        // 这里我们注意观察，返回的page数据结果是如何的？这样
        return Result.success(page);
    }


    /**
     * 支持带条件的分页检索
     *
     * @param pageNum  显示第几页
     * @param pageSize 每页显示几条记录
     * @param search   检索条件：家居名
     * @return
     */

    @GetMapping("/furnBySearchPage")
    public Result listFurnsByConditionPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "") String search
    ) {
        // 先创建
        QueryWrapper<Furn> queryWrapper = new QueryWrapper<>();

        // 判断search 是否有内容
        if (StringUtils.hasText(search)) {
            queryWrapper.like("name", search);
        }

        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), queryWrapper);

        return Result.success(page);
    }


    // 条件查询优化2
    @GetMapping("/furnBySearchPage2")
    public Result listFurnsByConditionPage2(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "") String search
    ) {
        // 先创建

        LambdaQueryWrapper<Furn> lambdaQueryWrapper = Wrappers.lambdaQuery();
        // 判断search 是否有内容
        if (StringUtils.hasText(search)) {
            lambdaQueryWrapper.like(Furn::getName, search);
        }

        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);

        log.info("page={", page.getRecords());
        return Result.success(page);
    }
}
