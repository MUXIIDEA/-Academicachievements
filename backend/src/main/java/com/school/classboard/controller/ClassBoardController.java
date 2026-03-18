package com.school.classboard.controller;

import com.school.classboard.dto.ClassBoardDTO;
import com.school.classboard.service.ClassBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 班牌展示控制器
 * 
 * 功能说明：
 * - 获取班牌展示数据
 * - 获取当前/下节课程
 * - 获取通知公告
 * 
 * @author School System
 * @version 1.0.0
 */
@RestController
@RequestMapping("/classboard")
@RequiredArgsConstructor
@Tag(name = "班牌展示", description = "班牌前端展示相关接口")
public class ClassBoardController {
    
    private final ClassBoardService classBoardService;
    
    /**
     * 根据教室ID获取班牌展示数据
     * 
     * @param roomId 教室ID
     * @return 班牌展示数据
     */
    @GetMapping("/data")
    @Operation(summary = "获取班牌数据", description = "根据教室ID获取完整的班牌展示数据")
    public ResponseEntity<ClassBoardDTO> getClassBoardData(@RequestParam Long roomId) {
        try {
            ClassBoardDTO data = classBoardService.getClassBoardData(roomId);
            return ResponseEntity.ok(data);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 根据设备ID获取班牌展示数据
     * 
     * @param deviceId 设备ID
     * @return 班牌展示数据
     */
    @GetMapping("/data/device/{deviceId}")
    @Operation(summary = "根据设备获取班牌数据", description = "根据班牌设备ID获取班牌展示数据")
    public ResponseEntity<ClassBoardDTO> getClassBoardDataByDevice(@PathVariable String deviceId) {
        try {
            ClassBoardDTO data = classBoardService.getClassBoardDataByDevice(deviceId);
            return ResponseEntity.ok(data);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 获取今日课表
     * 
     * @param classId 班级ID
     * @return 课程列表
     */
    @GetMapping("/schedule/today")
    @Operation(summary = "获取今日课表", description = "获取指定班级的今日课程安排")
    public ResponseEntity<Map<String, Object>> getTodaySchedule(@RequestParam Long classId) {
        var schedule = classBoardService.getTodaySchedule(classId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", schedule);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 获取当前课程
     * 
     * @param classId 班级ID
     * @return 当前课程信息
     */
    @GetMapping("/schedule/current")
    @Operation(summary = "获取当前课程", description = "获取当前正在上的课程")
    public ResponseEntity<Map<String, Object>> getCurrentLesson(@RequestParam Long classId) {
        var lesson = classBoardService.getCurrentLesson(classId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", lesson);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 获取下一节课
     * 
     * @param classId 班级ID
     * @return 下一节课信息
     */
    @GetMapping("/schedule/next")
    @Operation(summary = "获取下一节课", description = "获取下一节课程信息")
    public ResponseEntity<Map<String, Object>> getNextLesson(@RequestParam Long classId) {
        var lesson = classBoardService.getNextLesson(classId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", lesson);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 获取通知公告
     * 
     * @param classId 班级ID
     * @return 通知列表
     */
    @GetMapping("/notices")
    @Operation(summary = "获取通知公告", description = "获取当前有效的通知公告列表")
    public ResponseEntity<Map<String, Object>> getNotices(@RequestParam Long classId) {
        var notices = classBoardService.getEffectiveNotices(classId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", notices);
        return ResponseEntity.ok(result);
    }
}
