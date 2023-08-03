package com.my.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.model.system.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author qiao
 * @since 2023-07-31
 */
public interface SysUserService extends IService<SysUser> {

    void updateStatus(Long id, Integer status);
}
