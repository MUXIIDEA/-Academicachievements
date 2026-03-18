package com.school.classboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.classboard.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 教室数据访问层
 * 
 * @author School System
 * @version 1.0.0
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    
    /**
     * 根据设备ID查询教室
     * 
     * @param deviceId 设备ID
     * @return 教室信息
     */
    Room selectRoomByDeviceId(@Param("deviceId") String deviceId);
}
