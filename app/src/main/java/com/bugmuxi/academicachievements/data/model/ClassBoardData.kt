package com.bugmuxi.academicachievements.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalTime

/**
 * 班牌展示数据模型
 * 
 * 功能说明：
 * - 定义班牌展示数据的结构
 * - 包含教室、班级、天气、课程等信息
 * 
 * @author School System
 * @version 1.0.0
 */
data class ClassBoardData(
    @SerializedName("currentDate")
    val currentDate: String? = null,
    
    @SerializedName("currentTime")
    val currentTime: String? = null,
    
    @SerializedName("weekDay")
    val weekDay: Int = 1,
    
    @SerializedName("roomInfo")
    val roomInfo: RoomInfo? = null,
    
    @SerializedName("classInfo")
    val classInfo: ClassInfo? = null,
    
    @SerializedName("weatherInfo")
    val weatherInfo: WeatherInfo? = null,
    
    @SerializedName("todaySchedule")
    val todaySchedule: List<Lesson>? = emptyList(),
    
    @SerializedName("currentLesson")
    val currentLesson: Lesson? = null,
    
    @SerializedName("nextLesson")
    val nextLesson: Lesson? = null,
    
    @SerializedName("notices")
    val notices: List<Notice>? = emptyList()
)

/**
 * 教室信息
 */
data class RoomInfo(
    @SerializedName("id")
    val id: Long = 0,
    
    @SerializedName("roomCode")
    val roomCode: String = "",
    
    @SerializedName("roomName")
    val roomName: String = "",
    
    @SerializedName("building")
    val building: String = "",
    
    @SerializedName("floor")
    val floor: Int = 1
)

/**
 * 班级信息
 */
data class ClassInfo(
    @SerializedName("id")
    val id: Long = 0,
    
    @SerializedName("className")
    val className: String = "",
    
    @SerializedName("grade")
    val grade: String = "",
    
    @SerializedName("headTeacherName")
    val headTeacherName: String = "",
    
    @SerializedName("slogan")
    val slogan: String = "",
    
    @SerializedName("studentCount")
    val studentCount: Int = 0
)

/**
 * 天气信息
 */
data class WeatherInfo(
    @SerializedName("city")
    val city: String = "",
    
    @SerializedName("weather")
    val weather: String = "",
    
    @SerializedName("icon")
    val icon: String = "",
    
    @SerializedName("temperature")
    val temperature: Double = 0.0,
    
    @SerializedName("humidity")
    val humidity: Double = 0.0,
    
    @SerializedName("airQuality")
    val airQuality: String = ""
)

/**
 * 课程信息
 */
data class Lesson(
    @SerializedName("id")
    val id: Long = 0,
    
    @SerializedName("lessonOrder")
    val lessonOrder: Int = 0,
    
    @SerializedName("courseName")
    val courseName: String = "",
    
    @SerializedName("teacherName")
    val teacherName: String = "",
    
    @SerializedName("startTime")
    val startTime: String? = null,
    
    @SerializedName("endTime")
    val endTime: String? = null,
    
    @SerializedName("isCurrent")
    val isCurrent: Boolean = false
)

/**
 * 通知公告
 */
data class Notice(
    @SerializedName("id")
    val id: Long = 0,
    
    @SerializedName("title")
    val title: String = "",
    
    @SerializedName("content")
    val content: String = "",
    
    @SerializedName("publisherName")
    val publisherName: String = "",
    
    @SerializedName("publishTime")
    val publishTime: String = "",
    
    @SerializedName("isTop")
    val isTop: Int = 0
)
