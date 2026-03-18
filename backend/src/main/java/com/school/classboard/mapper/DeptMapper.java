package com.school.classboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.classboard.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 部门数据访问层
 * 
 * @author School System
 * @version 1.0.0
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    
    /**
     * 查询部门树形结构
     * 
     * @return 部门列表
     */
    List<Dept> selectDeptTree();
    
    /**
     * 根据父ID查询子部门
     * 
     * @param parentId 父部门ID
     * @return 子部门列表
     */
    List<Dept> selectChildrenByParentId(Long parentId);
}
