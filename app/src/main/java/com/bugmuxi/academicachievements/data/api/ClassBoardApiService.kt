package com.bugmuxi.academicachievements.data.api

import com.bugmuxi.academicachievements.data.model.ClassBoardData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 班牌API服务接口
 * 
 * 功能说明：
 * - 定义所有API请求方法
 * - 使用Retrofit进行网络请求
 * 
 * @author School System
 * @version 1.0.0
 */
interface ClassBoardApiService {
    
    /**
     * 根据教室ID获取班牌展示数据
     * 
     * @param roomId 教室ID
     * @return 班牌展示数据
     */
    @GET("classboard/data")
    suspend fun getClassBoardData(
        @Query("roomId") roomId: Long
    ): Response<ClassBoardData>
    
    /**
     * 根据设备ID获取班牌展示数据
     * 
     * @param deviceId 设备ID
     * @return 班牌展示数据
     */
    @GET("classboard/data/device/{deviceId}")
    suspend fun getClassBoardDataByDevice(
        @Path("deviceId") deviceId: String
    ): Response<ClassBoardData>
}
