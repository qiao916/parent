package com.my.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.model.system.SysMenu;
import com.my.vo.system.AssginMenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author qiao
 * @since 2023-08-03
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 菜单树形数据
     * @return
     */
    List<SysMenu> findNodes();

    List<SysMenu> findSysMenuByRoleId(Long roleId);

    void doAssign(AssginMenuVo assignMenuVo);
}
