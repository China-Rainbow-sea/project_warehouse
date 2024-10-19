package com.rainbowsea.smartcampus.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbowsea.smartcampus.pojo.Admin;
import com.rainbowsea.smartcampus.service.AdminService;
import com.rainbowsea.smartcampus.util.MD5;
import com.rainbowsea.smartcampus.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "系统管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {

    @Resource
    private AdminService adminService;

    // get
    @ApiOperation("分页获取所有Admin信息【带条件】")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("管理员名字") String adminName
    ) {
        Page<Admin> pageParam = new Page<>(pageNo, pageSize);

        Page<Admin> iPage = adminService.getAdminByOpr(pageParam, adminName);
        return Result.ok(iPage);
    }

    // post
    @ApiOperation("添加或修改Admin信息")
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(
            @ApiParam("json格式的admin信息") @RequestBody Admin admin
    ) {
        Integer id = admin.getId();
        if (id == null || id == 0 || (!Strings.isEmpty(admin.getPassword()))) {
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }


        adminService.saveOrUpdate(admin);
        return Result.ok();
    }

    // delete

    @ApiOperation("删除Admin信息")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(
            @ApiParam("要删除的管理员的多个ID的json集合") @RequestBody List<Integer> ids // RequestBody 将前端提交的json格式的字符串转换为后端java
            // 当中对应的 bean的类
    ) {
        adminService.removeByIds(ids);
        return Result.ok();
    }
}
