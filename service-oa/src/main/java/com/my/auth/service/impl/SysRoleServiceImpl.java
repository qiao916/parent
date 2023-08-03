package com.my.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.auth.mapper.SysRoleMapper;
import com.my.auth.mapper.SysUserRoleMapper;
import com.my.auth.service.SysRoleService;
import com.my.auth.service.SysUserRoleService;
import com.my.model.system.SysRole;
import com.my.model.system.SysUserRole;
import com.my.vo.system.AssginRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: oa-parent
 * @description:
 * @author: DY
 * @create: 2023-07-29 23:03
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 根据用户获取角色数据
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> findRoleByAdminId(int userId) {
//        1 查询所有角色
        List<SysRole> allRoleList = baseMapper.selectList(null);
//        2根据userid查询 角色用户关系表 查询userid 对应的所有角色id
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> existUserRoleList = sysUserRoleService.list(wrapper);

//从查询出来的用户id'对应角色list集合，获取所有角色id
//        List<Long> list = new ArrayList<>();
//        for (SysUserRole sysUserRole : existUserRoleList) {
//            Long roleId = sysUserRole.getRoleId();
//            list.add(roleId);
//        }
        List<Long> existRoleIdList = existUserRoleList.stream().map(c->c.getRoleId()).collect(Collectors.toList());

        List<SysRole> assginRoleList = new ArrayList<>();
        for (SysRole role : allRoleList) {
            //已分配
            if (existRoleIdList.contains(role.getId())) {
                assginRoleList.add(role);
            }
        }


        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assginRoleList", assginRoleList);
        roleMap.put("allRolesList", allRoleList);
        return roleMap;
    }

    /**
     * 分配角色
     *
     * @param assginRoleVo
     */
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
//        把用户之前分配的角色数据删除 你在用户关系表里 根据user_id删除
        LambdaQueryWrapper<SysUserRole>wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,assginRoleVo.getUserId());
        sysUserRoleService.remove(wrapper);
//        重新分配
        List<Long> roleIdList=assginRoleVo.getRoleIdList();
        for (Long roleId:roleIdList) {
            if (StringUtils.isEmpty(roleId)){
                continue;
            }
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleService.save(userRole);
        }

    }
}
