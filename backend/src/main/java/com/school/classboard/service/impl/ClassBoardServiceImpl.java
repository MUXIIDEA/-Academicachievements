package com.school.classboard.service.impl;

import cn.hutool.core.date.DateUtil;
import com.school.classboard.dto.ClassBoardDTO;
import com.school.classboard.entity.ClassInfo;
import com.school.classboard.entity.Notice;
import com.school.classboard.entity.Room;
import com.school.classboard.entity.Schedule;
import com.school.classboard.mapper.ClassInfoMapper;
import com.school.classboard.mapper.NoticeMapper;
import com.school.classboard.mapper.RoomMapper;
import com.school.classboard.mapper.ScheduleMapper;
import com.school.classboard.service.ClassBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 班牌展示服务实现类
 * 
 * @author School System
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ClassBoardServiceImpl implements ClassBoardService {
    
    private final RoomMapper roomMapper;
    private final ClassInfoMapper classInfoMapper;
    private final ScheduleMapper scheduleMapper;
    private final NoticeMapper noticeMapper;
    
    @Override
    public ClassBoardDTO getClassBoardData(Long roomId) {
        Room room = roomMapper.selectById(roomId);
        if (room == null) {
            throw new RuntimeException("教室不存在");
        }
        return buildClassBoardData(room);
    }
    
    @Override
    public ClassBoardDTO getClassBoardDataByDevice(String deviceId) {
        Room room = roomMapper.selectRoomByDeviceId(deviceId);
        if (room == null) {
            throw new RuntimeException("设备未绑定教室");
        }
        return buildClassBoardData(room);
    }
    
    private ClassBoardDTO buildClassBoardData(Room room) {
        ClassBoardDTO dto = new ClassBoardDTO();
        
        // 设置日期时间
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        dto.setCurrentDate(today);
        dto.setCurrentTime(now);
        dto.setWeekDay(today.getDayOfWeek().getValue());
        
        // 设置教室信息
        ClassBoardDTO.RoomInfo roomInfo = new ClassBoardDTO.RoomInfo();
        roomInfo.setId(room.getId());
        roomInfo.setRoomCode(room.getRoomCode());
        roomInfo.setRoomName(room.getRoomName());
        roomInfo.setBuilding(room.getBuilding());
        roomInfo.setFloor(room.getFloor());
        dto.setRoomInfo(roomInfo);
        
        // 查询绑定的班级
        ClassInfo classInfo = classInfoMapper.selectClassByRoomId(room.getId());
        if (classInfo != null) {
            ClassBoardDTO.ClassInfo classInfoDTO = new ClassBoardDTO.ClassInfo();
            classInfoDTO.setId(classInfo.getId());
            classInfoDTO.setClassName(classInfo.getClassName());
            classInfoDTO.setGrade(classInfo.getGrade());
            classInfoDTO.setHeadTeacherName(classInfo.getHeadTeacherName());
            classInfoDTO.setSlogan(classInfo.getSlogan());
            classInfoDTO.setStudentCount(classInfo.getStudentCount());
            dto.setClassInfo(classInfoDTO);
            
            // 获取课表
            Integer weekDay = today.getDayOfWeek().getValue();
            dto.setTodaySchedule(getTodaySchedule(classInfo.getId()));
            dto.setCurrentLesson(getCurrentLesson(classInfo.getId()));
            dto.setNextLesson(getNextLesson(classInfo.getId()));
            
            // 获取通知
            dto.setNotices(getEffectiveNotices(classInfo.getId()));
        }
        
        // TODO: 获取天气信息
        ClassBoardDTO.WeatherInfo weatherInfo = new ClassBoardDTO.WeatherInfo();
        weatherInfo.setCity(room.getCity() != null ? room.getCity() : "北京");
        weatherInfo.setWeather("晴");
        weatherInfo.setIcon("sunny");
        weatherInfo.setTemperature(25.0);
        weatherInfo.setHumidity(60.0);
        weatherInfo.setAirQuality("优");
        dto.setWeatherInfo(weatherInfo);
        
        return dto;
    }
    
    @Override
    public List<ClassBoardDTO.LessonDTO> getTodaySchedule(Long classId) {
        Integer weekDay = LocalDate.now().getDayOfWeek().getValue();
        List<Schedule> schedules = scheduleMapper.selectScheduleByClassAndWeek(classId, weekDay);
        
        LocalTime now = LocalTime.now();
        return schedules.stream().map(schedule -> {
            ClassBoardDTO.LessonDTO dto = new ClassBoardDTO.LessonDTO();
            dto.setId(schedule.getId());
            dto.setLessonOrder(schedule.getLessonOrder());
            dto.setCourseName(schedule.getCourseName());
            dto.setTeacherName(schedule.getTeacherName());
            dto.setStartTime(schedule.getStartTime());
            dto.setEndTime(schedule.getEndTime());
            dto.setIsCurrent(now.isAfter(schedule.getStartTime()) && now.isBefore(schedule.getEndTime()));
            return dto;
        }).collect(Collectors.toList());
    }
    
    @Override
    public ClassBoardDTO.LessonDTO getCurrentLesson(Long classId) {
        Integer weekDay = LocalDate.now().getDayOfWeek().getValue();
        LocalTime now = LocalTime.now();
        Schedule schedule = scheduleMapper.selectCurrentLesson(classId, weekDay, now);
        
        if (schedule == null) {
            return null;
        }
        
        ClassBoardDTO.LessonDTO dto = new ClassBoardDTO.LessonDTO();
        dto.setId(schedule.getId());
        dto.setLessonOrder(schedule.getLessonOrder());
        dto.setCourseName(schedule.getCourseName());
        dto.setTeacherName(schedule.getTeacherName());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setIsCurrent(true);
        return dto;
    }
    
    @Override
    public ClassBoardDTO.LessonDTO getNextLesson(Long classId) {
        Integer weekDay = LocalDate.now().getDayOfWeek().getValue();
        LocalTime now = LocalTime.now();
        Schedule schedule = scheduleMapper.selectNextLesson(classId, weekDay, now);
        
        if (schedule == null) {
            return null;
        }
        
        ClassBoardDTO.LessonDTO dto = new ClassBoardDTO.LessonDTO();
        dto.setId(schedule.getId());
        dto.setLessonOrder(schedule.getLessonOrder());
        dto.setCourseName(schedule.getCourseName());
        dto.setTeacherName(schedule.getTeacherName());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setIsCurrent(false);
        return dto;
    }
    
    @Override
    public List<ClassBoardDTO.NoticeDTO> getEffectiveNotices(Long classId) {
        // 获取全校通知和班级通知
        List<Notice> notices = noticeMapper.selectEffectiveNotices("0," + classId);
        
        return notices.stream().map(notice -> {
            ClassBoardDTO.NoticeDTO dto = new ClassBoardDTO.NoticeDTO();
            dto.setId(notice.getId());
            dto.setTitle(notice.getTitle());
            dto.setContent(notice.getContent());
            dto.setPublisherName(notice.getPublisherName());
            dto.setPublishTime(notice.getPublishTime() != null ? 
                notice.getPublishTime().toString() : null);
            dto.setIsTop(notice.getIsTop());
            return dto;
        }).collect(Collectors.toList());
    }
}
