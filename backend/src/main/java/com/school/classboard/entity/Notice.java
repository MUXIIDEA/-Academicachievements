package com.school.classboard.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 通知公告实体类
 * 
 * 功能说明：
 * - 存储校园通知公告
 * - 支持富文本内容
 * - 支持有效期设置
 * 
 * @author School System
 * @version 1.0.0
 */
@Data
@TableName("edu_notice")
public class Notice {
    
    /**
     * 通知ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 通知标题
     */
    private String title;
    
    /**
     * 通知内容(富文本)
     */
    private String content;
    
    /**
     * 通知类型: 1-学校通知, 2-年级通知, 3-班级通知
     */
    private Integer noticeType;
    
    /**
     * 发布范围: 0-全校, 具体班级ID
     */
    private String scope;
    
    /**
     * 发布人ID
     */
    private Long publisherId;
    
    /**
     * 发布人姓名
     */
    private String publisherName;
    
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    
    /**
     * 生效开始时间
     */
    private LocalDateTime effectStartTime;
    
    /**
     * 生效结束时间
     */
    private LocalDateTime effectEndTime;
    
    /**
     * 是否置顶: 0-否, 1-是
     */
    private Integer isTop;
    
    /**
     * 浏览次数
     */
    private Integer viewCount;
    
    /**
     * 状态: 0-草稿, 1-已发布, 2-已撤回
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
