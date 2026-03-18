package com.school.classboard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.classboard.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 用户服务接口
 * 
 * @author School System
 * @version 1.0.0
 */
public interface UserService extends IService<User> {
    
    /**
     * 分页查询用户列表
     * 
     * @param page 分页对象
     * @param username 用户名(模糊查询)
     * @param realName 真实姓名(模糊查询)
     * @param deptId 部门ID
     * @return 分页结果
     */
    IPage<User> selectUserPage(IPage<User> page, String username, String realName, Long deptId);
    
    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 用户对象
     */
    User selectUserByUsername(String username);
    
    /**
     * 新增用户
     * 
     * @param user 用户对象
     * @return 是否成功
     */
    boolean createUser(User user);
    
    /**
     * 修改用户
     * 
     * @param user 用户对象
     * @return 是否成功
     */
    boolean updateUser(User user);
    
    /**
     * 修改用户密码
     * 
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 是否成功
     */
    boolean updatePassword(Long userId, String oldPassword, String newPassword);
    
    /**
     * 重置用户密码
     * 
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean resetPassword(Long userId);
    
    /**
     * 修改用户状态
     * 
     * @param userId 用户ID
     * @param status 状态
     * @return 是否成功
     */
    boolean updateStatus(Long userId, Integer status);
    
    /**
     * 分配用户角色
     * 
     * @param userId 用户ID
     * @param roleIds 角色ID列表
     * @return 是否成功
     */
    boolean assignRoles(Long userId, List<Long> roleIds);
}
