package com.school.classboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.classboard.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户数据访问层
 * 
 * 功能说明：
 * - 继承MyBatis Plus BaseMapper
 * - 提供基础CRUD操作
 * - 自定义复杂查询方法
 * 
 * @author School System
 * @version 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
    /**
     * 根据用户名查询用户(包含部门信息)
     * 
     * @param username 用户名
     * @return 用户对象
     */
    User selectUserWithDept(@Param("username") String username);
    
    /**
     * 根据部门ID查询用户列表
     * 
     * @param deptId 部门ID
     * @return 用户列表
     */
    List<User> selectUsersByDeptId(@Param("deptId") Long deptId);
    
    /**
     * 更新用户密码
     * 
     * @param userId 用户ID
     * @param password 新密码
     * @return 影响行数
     */
    int updatePassword(@Param("userId") Long userId, @Param("password") String password);
    
    /**
     * 重置用户密码为默认密码
     * 
     * @param userId 用户ID
     * @return 影响行数
     */
    int resetPassword(@Param("userId") Long userId);
}
