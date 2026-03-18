package com.school.classboard.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 角色实体类
 * 
 * 功能说明：
 * - 定义用户角色
 * - 关联权限菜单
 * 
 * @author School System
 * @version 1.0.0
 */
@Data
@TableName("sys_role")
public class Role {
    
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色标识
     */
    private String roleKey;
    
    /**
     * 显示顺序
     */
    private Integer orderNum;
    
    /**
     * 角色状态: 0-禁用, 1-启用
     */
    private Integer status;
    
    /**
     * 逻辑删除标记
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
}
