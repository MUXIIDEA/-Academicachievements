package com.school.classboard.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 教室实体类
 * 
 * 功能说明：
 * - 存储教室基本信息
 * - 关联班牌设备
 * 
 * @author School System
 * @version 1.0.0
 */
@Data
@TableName("edu_room")
public class Room {
    
    /**
     * 教室ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 教室编号 (如: 302)
     */
    private String roomCode;
    
    /**
     * 教室名称 (如: 高一(3)班教室)
     */
    private String roomName;
    
    /**
     * 楼栋
     */
    private String building;
    
    /**
     * 楼层
     */
    private Integer floor;
    
    /**
     * 容纳人数
     */
    private Integer capacity;
    
    /**
     * 设备编号(班牌设备ID)
     */
    private String deviceId;
    
    /**
     * 所在城市(用于天气查询)
     */
    private String city;
    
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
    
    /**
     * 绑定的班级信息(非数据库字段)
     */
    @TableField(exist = false)
    private ClassInfo classInfo;
}
