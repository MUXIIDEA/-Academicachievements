package com.school.classboard.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 班牌展示数据传输对象
 * 
 * @author School System
 * @version 1.0.0
 */
@Data
public class ClassBoardDTO {
    
    /**
     * 当前日期
     */
    private LocalDate currentDate;
    
    /**
     * 当前时间
     */
    private LocalTime currentTime;
    
    /**
     * 星期几
     */
    private Integer weekDay;
    
    /**
     * 教室信息
     */
    private RoomInfo roomInfo;
    
    /**
     * 班级信息
     */
    private ClassInfo classInfo;
    
    /**
     * 天气信息
     */
    private WeatherInfo weatherInfo;
    
    /**
     * 今日课表
     */
    private List<LessonDTO> todaySchedule;
    
    /**
     * 当前课程
     */
    private LessonDTO currentLesson;
    
    /**
     * 下一节课
     */
    private LessonDTO nextLesson;
    
    /**
     * 通知公告列表
     */
    private List<NoticeDTO> notices;
    
    @Data
    public static class RoomInfo {
        private Long id;
        private String roomCode;
        private String roomName;
        private String building;
        private Integer floor;
    }
    
    @Data
    public static class ClassInfo {
        private Long id;
        private String className;
        private String grade;
        private String headTeacherName;
        private String slogan;
        private Integer studentCount;
    }
    
    @Data
    public static class WeatherInfo {
        private String city;
        private String weather;
        private String icon;
        private Double temperature;
        private Double humidity;
        private String airQuality;
    }
    
    @Data
    public static class LessonDTO {
        private Long id;
        private Integer lessonOrder;
        private String courseName;
        private String teacherName;
        private LocalTime startTime;
        private LocalTime endTime;
        private Boolean isCurrent;
    }
    
    @Data
    public static class NoticeDTO {
        private Long id;
        private String title;
        private String content;
        private String publisherName;
        private String publishTime;
        private Integer isTop;
    }
}
