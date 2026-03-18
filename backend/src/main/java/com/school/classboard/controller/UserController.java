package com.school.classboard.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.classboard.entity.User;
import com.school.classboard.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理控制器
 * 
 * 功能说明：
 * - 用户列表查询（分页）
 * - 用户新增、修改、删除
 * - 密码管理（修改、重置）
 * - 用户状态管理
 * - 角色分配
 * 
 * @author School System
 * @version 1.0.0
 */
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户管理相关接口")
public class UserController {
    
    private final UserService userService;
    
    /**
     * 分页查询用户列表
     * 
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param username 用户名(模糊查询)
     * @param realName 真实姓名(模糊查询)
     * @param deptId 部门ID
     * @return 分页结果
     */
    @GetMapping("/list")
    @Operation(summary = "获取用户列表", description = "分页查询用户信息")
    public ResponseEntity<Map<String, Object>> getUserList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) Long deptId) {
        
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> result = userService.selectUserPage(page, username, realName, deptId);
        
        Map<String, Object> map = new HashMap<>();
        map.put("total", result.getTotal());
        map.put("records", result.getRecords());
        map.put("pages", result.getPages());
        map.put("current", result.getCurrent());
        map.put("size", result.getSize());
        
        return ResponseEntity.ok(map);
    }
    
    /**
     * 根据ID查询用户详情
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/{userId}")
    @Operation(summary = "获取用户详情", description = "根据用户ID查询用户详细信息")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
    /**
     * 新增用户
     * 
     * @param user 用户信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增用户", description = "创建一个新的用户账号")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        boolean success = userService.createUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", success ? "用户创建成功" : "用户创建失败");
        return ResponseEntity.ok(result);
    }
    
    /**
     * 修改用户信息
     * 
     * @param userId 用户ID
     * @param user 用户信息
     * @return 操作结果
     */
    @PutMapping("/{userId}")
    @Operation(summary = "修改用户", description = "更新用户基本信息")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable Long userId,
            @RequestBody User user) {
        
        user.setId(userId);
        boolean success = userService.updateUser(user);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", success ? "用户更新成功" : "用户更新失败");
        return ResponseEntity.ok(result);
    }
    
    /**
     * 删除用户
     * 
     * @param userId 用户ID
     * @return 操作结果
     */
    @DeleteMapping("/{userId}")
    @Operation(summary = "删除用户", description = "删除指定的用户(逻辑删除)")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long userId) {
        boolean success = userService.removeById(userId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", success ? "用户删除成功" : "用户删除失败");
        return ResponseEntity.ok(result);
    }
    
    /**
     * 修改用户密码
     * 
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 操作结果
     */
    @PutMapping("/{userId}/password")
    @Operation(summary = "修改密码", description = "用户修改自己的登录密码")
    public ResponseEntity<Map<String, Object>> updatePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> passwordMap) {
        
        try {
            String oldPassword = passwordMap.get("oldPassword");
            String newPassword = passwordMap.get("newPassword");
            
            boolean success = userService.updatePassword(userId, oldPassword, newPassword);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", success);
            result.put("message", success ? "密码修改成功" : "密码修改失败");
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    /**
     * 重置用户密码
     * 
     * @param userId 用户ID
     * @return 操作结果
     */
    @PutMapping("/{userId}/resetPassword")
    @Operation(summary = "重置密码", description = "管理员重置用户密码为123456")
    public ResponseEntity<Map<String, Object>> resetPassword(@PathVariable Long userId) {
        boolean success = userService.resetPassword(userId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", success ? "密码重置成功，新密码为: 123456" : "密码重置失败");
        return ResponseEntity.ok(result);
    }
    
    /**
     * 修改用户状态
     * 
     * @param userId 用户ID
     * @param status 状态(0-禁用, 1-启用)
     * @return 操作结果
     */
    @PutMapping("/{userId}/status")
    @Operation(summary = "修改状态", description = "启用或禁用用户账号")
    public ResponseEntity<Map<String, Object>> updateStatus(
            @PathVariable Long userId,
            @RequestParam Integer status) {
        
        boolean success = userService.updateStatus(userId, status);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", success ? "状态修改成功" : "状态修改失败");
        return ResponseEntity.ok(result);
    }
}
