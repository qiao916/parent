package com.my.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.auth.service.SysUserService;
import com.my.auth.service.impl.SysUserServiceImpl;
import com.my.common.result.Result;
import com.my.model.system.SysRole;
import com.my.model.system.SysUser;
import com.my.vo.system.SysRoleQueryVo;
import com.my.vo.system.SysUserQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qiao
 * @since 2023-07-31
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService service;

    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        service.updateStatus(id, status);
        return Result.ok();
    }
  


    @ApiOperation(value = "获取全部用户列表")
    @GetMapping("findAll")
    public Result<List<SysUser>> findAll() {
        List<SysUser> roleList = service.list();

        return Result.ok(roleList);
    }

    @ApiOperation(value = "分页条件查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable int page, @PathVariable int limit,
                                SysUserQueryVo sysUserQueryVo) {
//        1创建page对象 传递分页相关参数
        Page<SysUser> page1 = new Page(page, limit);
//        2封装条件 判断条件是否为空
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        String keyword = sysUserQueryVo.getKeyword();
        String createTimeBegin = sysUserQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysUserQueryVo.getCreateTimeEnd();
        if (StringUtils.isEmpty(keyword) == false) {
            wrapper.like(SysUser::getUsername, keyword);
        }
        //ge 大于等于
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge(SysUser::getCreateTime,createTimeBegin);
        }
        //le 小于等于
        if(!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le(SysUser::getCreateTime,createTimeEnd);
        }
//        3调用方法实现
        Page<SysUser> page2 = service.page(page1, wrapper);
        return Result.ok(page2);

    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser sysUser) {

        boolean is_success = service.save(sysUser);
        if (is_success) {
            return Result.ok();
        }

        return Result.fail();
    }


    @ApiOperation(value = "根据Id查找用户")
    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        SysUser sysUser = service.getById(id);
        return Result.ok(sysUser);

    }

    @ApiOperation(value = "修改用户")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysUser sysUser) {
        boolean is_success = service.updateById(sysUser);
        if (is_success) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        boolean is_success = service.removeById(id);
        if (is_success) {
            return Result.ok();
        }

        return Result.fail();
    }

    @ApiOperation(value = "批量删除用户")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Integer> list) {
        boolean is_success = service.removeByIds(list);
        if (is_success) {
            return Result.ok();
        }

        return Result.fail();
    }


}

