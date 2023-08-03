package com.my.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.auth.service.SysRoleService;
import com.my.common.execption.MyException;
import com.my.common.result.Result;
import com.my.model.system.SysRole;
import com.my.vo.system.AssginRoleVo;
import com.my.vo.system.SysRoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: oa-parent
 * @description:
 * @author: DY
 * @create: 2023-07-29 22:56
 **/
@RestController
@Api(tags = "角色管理")
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable int userId) {
        Map<String, Object> roleMap = sysRoleService.findRoleByAdminId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }




    @ApiOperation(value = "获取全部角色列表")
    @GetMapping("findAll")
    public Result<List<SysRole>> findAll() {
        List<SysRole> roleList = sysRoleService.list();
//        try {
//            int i = 10 / 0;//设置异常
//
//        } catch (Exception e) {
//            throw new MyException(20001,"除数为0");
//
//        }
        return Result.ok(roleList);
    }

    @ApiOperation(value = "分页条件查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable int page, @PathVariable int limit,
                                SysRoleQueryVo sysRoleQueryVo) {
//        1创建page对象 传递分页相关参数
        Page<SysRole> page1 = new Page(page, limit);
//        2封装条件 判断条件是否为空
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (StringUtils.isEmpty(roleName) == false) {
            wrapper.like(SysRole::getRoleName, roleName);
        }
//        3调用方法实现
        Page<SysRole> page2 = sysRoleService.page(page1, wrapper);
        return Result.ok(page2);

    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysRole sysRole) {

        boolean is_success = sysRoleService.save(sysRole);
        if (is_success) {
            return Result.ok();
        }

        return Result.fail();
    }


    @ApiOperation(value = "根据Id查找用户")
    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);

    }

    @ApiOperation(value = "修改用户")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysRole sysRole) {
        boolean is_success = sysRoleService.updateById(sysRole);
        if (is_success) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        boolean is_success = sysRoleService.removeById(id);
        if (is_success) {
            return Result.ok();
        }

        return Result.fail();
    }

    @ApiOperation(value = "批量删除用户")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Integer> list) {
        boolean is_success = sysRoleService.removeByIds(list);
        if (is_success) {
            return Result.ok();
        }

        return Result.fail();
    }


}
