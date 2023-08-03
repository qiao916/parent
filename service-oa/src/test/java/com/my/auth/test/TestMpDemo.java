package com.my.auth.test;

import com.my.auth.mapper.SysRoleMapper;
import com.my.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestMpDemo {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void getAll() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole s :sysRoles) {
            System.out.println(s);

        }

    }
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");

        int result = sysRoleMapper.insert(sysRole);
        System.out.println(result); //影响的行数
        System.out.println(sysRole); //id自动回填
    }
    @Test
    public void testUpdateById(){
//        查找
        SysRole sysRole=sysRoleMapper.selectById(9);
//        设置修改值
        sysRole.setRoleName("角色管理员1");
//        更改
        int result = sysRoleMapper.updateById(sysRole);
        System.out.println(result);

    }
    @Test
    public void testDeleteById(){
//删除
        int result = sysRoleMapper.deleteById(9);
        System.out.println(result);

    }
    @Test
    public void testDeleteBatchIds() {
//        批量删除
        int result = sysRoleMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(result);
    }


}
