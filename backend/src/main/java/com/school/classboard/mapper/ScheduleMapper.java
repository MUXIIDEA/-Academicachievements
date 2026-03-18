package com.school.classboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.classboard.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalTime;
import java.util.List;

/**
 * 课程表数据访问层
 * 
 * @author School System
 * @version 1.0.0
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    
    /**
     * 根据班级ID和星期查询课程列表
     * 
     * @param classId 班级ID
     * @param weekDay 星期几
     * @return 课程列表
     */
    List<Schedule> selectScheduleByClassAndWeek(@Param("classId") Long classId, @Param("weekDay") Integer weekDay);
    
    /**
     * 查询当前正在上的课
     * 
     * @param classId 班级ID
     * @param weekDay 星期几
     * @param currentTime 当前时间
     * @return 当前课程
     */
    Schedule selectCurrentLesson(@Param("classId") Long classId, @Param("weekDay") Integer weekDay, @Param("currentTime") LocalTime currentTime);
    
    /**
     * 查询下一节课
     * 
     * @param classId 班级ID
     * @param weekDay 星期几
     * @param currentTime 当前时间
     * @return 下一节课
     */
    Schedule selectNextLesson(@Param("classId") Long classId, @Param("weekDay") Integer weekDay, @Param("currentTime") LocalTime currentTime);
}
