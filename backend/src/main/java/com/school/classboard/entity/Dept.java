package com.school.classboard.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 部门实体类
 * 
 * 功能说明：
 * - 组织架构树形结构
 * - 支持多级部门
 * 
 * @author School System
 * @version 1.0.0
 */
@Data
@TableName("sys_dept")
public class Dept {
    
    /**
     * 部门ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 部门名称
     */
    private String deptName;
    
    /**
     * 父部门ID (0表示顶级部门)
     */
    private Long parentId;
    
    /**
     * 部门层级路径 (如: 0,1,2)
     */
    private String ancestors;
    
    /**
     * 排序号
     */
    private Integer orderNum;
    
    /**
     * 负责人
     */
    private String leader;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 部门状态: 0-禁用, 1-启用
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
