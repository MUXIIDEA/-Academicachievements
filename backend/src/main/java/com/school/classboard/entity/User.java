package com.school.classboard.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户实体类
 * 
 * 功能说明：
 * - 存储系统用户信息
 * - 支持逻辑删除
 * - 记录创建和更新时间
 * 
 * @author School System
 * @version 1.0.0
 */
@Data
@TableName("sys_user")
public class User {
    
    /**
     * 用户ID (主键，自增)
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 登录账号
     */
    private String username;
    
    /**
     * 密码(加密存储)
     */
    private String password;
    
    /**
     * 用户姓名
     */
    private String realName;
    
    /**
     * 所属部门ID
     */
    private Long deptId;
    
    /**
     * 手机号码
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 用户状态: 0-禁用, 1-启用
     */
    private Integer status;
    
    /**
     * 逻辑删除: 0-未删除, 1-已删除
     */
    @TableLogic
    private Integer deleted;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    /**
     * 部门名称(非数据库字段)
     */
    @TableField(exist = false)
    private String deptName;
}
