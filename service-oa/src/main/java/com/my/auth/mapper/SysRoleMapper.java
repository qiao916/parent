package com.my.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.model.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
}
