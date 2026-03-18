package com.school.classboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.classboard.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 角色数据访问层
 * 
 * @author School System
 * @version 1.0.0
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    
    /**
     * 根据用户ID查询角色列表
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> selectRolesByUserId(Long userId);
}
