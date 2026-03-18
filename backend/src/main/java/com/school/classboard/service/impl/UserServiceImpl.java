package com.school.classboard.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.classboard.entity.User;
import com.school.classboard.mapper.UserMapper;
import com.school.classboard.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 * 用户服务实现类
 * 
 * @author School System
 * @version 1.0.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    private static final String DEFAULT_PASSWORD = "123456";
    
    @Override
    public IPage<User> selectUserPage(IPage<User> page, String username, String realName, Long deptId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(username), User::getUsername, username)
               .like(StringUtils.hasText(realName), User::getRealName, realName)
               .eq(deptId != null, User::getDeptId, deptId)
               .orderByDesc(User::getCreateTime);
        return this.page(page, wrapper);
    }
    
    @Override
    public User selectUserByUsername(String username) {
        return baseMapper.selectUserWithDept(username);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createUser(User user) {
        // 密码加密
        user.setPassword(BCrypt.hashpw(DEFAULT_PASSWORD));
        user.setStatus(1);
        user.setDeleted(0);
        return this.save(user);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(User user) {
        return this.updateById(user);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = this.getById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 验证旧密码
        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            throw new RuntimeException("旧密码错误");
        }
        
        // 更新新密码
        return baseMapper.updatePassword(userId, BCrypt.hashpw(newPassword)) > 0;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resetPassword(Long userId) {
        return baseMapper.updatePassword(userId, BCrypt.hashpw(DEFAULT_PASSWORD)) > 0;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStatus(Long userId, Integer status) {
        User user = new User();
        user.setId(userId);
        user.setStatus(status);
        return this.updateById(user);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean assignRoles(Long userId, List<Long> roleIds) {
        // TODO: 实现用户角色关联表操作
        return true;
    }
}
