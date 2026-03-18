package com.school.classboard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.classboard.entity.ClassInfo;
import com.school.classboard.entity.Room;
import com.school.classboard.dto.ClassBoardDTO;
import java.util.List;

/**
 * 班牌展示服务接口
 * 
 * @author School System
 * @version 1.0.0
 */
public interface ClassBoardService {
    
    /**
     * 根据教室ID获取班牌展示数据
     * 
     * @param roomId 教室ID
     * @return 班牌展示数据
     */
    ClassBoardDTO getClassBoardData(Long roomId);
    
    /**
     * 根据设备ID获取班牌展示数据
     * 
     * @param deviceId 设备ID
     * @return 班牌展示数据
     */
    ClassBoardDTO getClassBoardDataByDevice(String deviceId);
    
    /**
     * 获取今日课表
     * 
     * @param classId 班级ID
     * @return 课程列表
     */
    List<ClassBoardDTO.LessonDTO> getTodaySchedule(Long classId);
    
    /**
     * 获取当前课程
     * 
     * @param classId 班级ID
     * @return 当前课程
     */
    ClassBoardDTO.LessonDTO getCurrentLesson(Long classId);
    
    /**
     * 获取下一节课
     * 
     * @param classId 班级ID
     * @return 下一节课
     */
    ClassBoardDTO.LessonDTO getNextLesson(Long classId);
    
    /**
     * 获取有效通知公告
     * 
     * @param classId 班级ID
     * @return 通知列表
     */
    List<ClassBoardDTO.NoticeDTO> getEffectiveNotices(Long classId);
}
