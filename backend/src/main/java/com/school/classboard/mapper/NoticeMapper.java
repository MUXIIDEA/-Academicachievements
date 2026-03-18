package com.school.classboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.classboard.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 通知公告数据访问层
 * 
 * @author School System
 * @version 1.0.0
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    
    /**
     * 查询有效的通知公告列表
     * 
     * @param scope 范围(班级ID或0表示全校)
     * @return 通知列表
     */
    List<Notice> selectEffectiveNotices(@Param("scope") String scope);
}
