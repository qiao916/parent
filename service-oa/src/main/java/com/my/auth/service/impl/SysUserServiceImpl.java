package com.my.auth.service.impl;

import com.my.auth.mapper.SysUserMapper;
import com.my.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.model.system.SysUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author qiao
 * @since 2023-07-31
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public void updateStatus(Long id, Integer status) {
        SysUser sysUser=baseMapper.selectById(id);

        if (status==1){
            sysUser.setStatus(1);
        }else {
            sysUser.setStatus(0);
        }
        baseMapper.updateById(sysUser);

    }
}
