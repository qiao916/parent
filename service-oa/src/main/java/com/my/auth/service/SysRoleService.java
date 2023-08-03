package com.my.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.model.system.SysRole;
import com.my.vo.system.AssginRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {

    Map<String, Object> findRoleByAdminId(int userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
