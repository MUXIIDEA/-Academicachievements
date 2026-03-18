package com.school.classboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.classboard.entity.ClassInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 班级数据访问层
 * 
 * @author School System
 * @version 1.0.0
 */
@Mapper
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {
    
    /**
     * 根据教室ID查询班级
     * 
     * @param roomId 教室ID
     * @return 班级信息
     */
    ClassInfo selectClassByRoomId(@Param("roomId") Long roomId);
    
    /**
     * 根据年级查询班级列表
     * 
     * @param grade 年级
     * @return 班级列表
     */
    List<ClassInfo> selectClassesByGrade(@Param("grade") String grade);
}
