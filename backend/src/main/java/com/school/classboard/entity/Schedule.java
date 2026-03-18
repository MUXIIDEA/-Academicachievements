package com.school.classboard.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 课程表实体类
 * 
 * 功能说明：
 * - 存储课程安排信息
 * - 支持每周循环课表
 * 
 * @author School System
 * @version 1.0.0
 */
@Data
@TableName("edu_schedule")
public class Schedule {
    
    /**
     * 课程ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 班级ID
     */
    private Long classId;
    
    /**
     * 星期几 (1-7)
     */
    private Integer weekDay;
    
    /**
     * 第几节课
     */
    private Integer lessonOrder;
    
    /**
     * 课程名称
     */
    private String courseName;
    
    /**
     * 教师ID
     */
    private Long teacherId;
    
    /**
     * 教师姓名(冗余字段)
     */
    private String teacherName;
    
    /**
     * 开始时间
     */
    private LocalTime startTime;
    
    /**
     * 结束时间
     */
    private LocalTime endTime;
    
    /**
     * 教室ID
     */
    private Long roomId;
    
    /**
     * 学期 (如: 2024-2025-1)
     */
    private String semester;
    
    /**
     * 备注
     */
    private String remark;
    
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
