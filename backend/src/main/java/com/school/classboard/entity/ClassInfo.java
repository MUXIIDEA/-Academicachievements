package com.school.classboard.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 班级实体类
 * 
 * 功能说明：
 * - 存储班级基本信息
 * - 关联班主任和教室
 * 
 * @author School System
 * @version 1.0.0
 */
@Data
@TableName("edu_class")
public class ClassInfo {
    
    /**
     * 班级ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 班级名称 (如: 高一(3)班)
     */
    private String className;
    
    /**
     * 班级编码
     */
    private String classCode;
    
    /**
     * 年级 (如: 高一、高二)
     */
    private String grade;
    
    /**
     * 班主任ID
     */
    private Long headTeacherId;
    
    /**
     * 班主任姓名(冗余字段)
     */
    private String headTeacherName;
    
    /**
     * 绑定教室ID
     */
    private Long roomId;
    
    /**
     * 班级口号/格言
     */
    private String slogan;
    
    /**
     * 学生人数
     */
    private Integer studentCount;
    
    /**
     * 状态: 0-停用, 1-启用
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
