package com.bugmuxi.academicachievements.data.repository

import com.bugmuxi.academicachievements.data.api.RetrofitClient
import com.bugmuxi.academicachievements.data.model.ClassBoardData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 班牌数据仓库
 * 
 * 功能说明：
 * - 封装数据访问逻辑
 * - 处理网络请求和错误
 * - 提供数据缓存
 * 
 * @author School System
 * @version 1.0.0
 */
class ClassBoardRepository {
    
    private val apiService = RetrofitClient.classBoardApiService
    
    // 缓存的班牌数据
    private var cachedData: ClassBoardData? = null
    
    /**
     * 根据教室ID获取班牌数据
     * 
     * @param roomId 教室ID
     * @return 班牌数据结果
     */
    suspend fun getClassBoardData(roomId: Long): Result<ClassBoardData> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getClassBoardData(roomId)
                if (response.isSuccessful && response.body() != null) {
                    cachedData = response.body()
                    Result.success(cachedData!!)
                } else {
                    // 如果网络失败,返回缓存数据
                    cachedData?.let { Result.success(it) }
                        ?: Result.failure(Exception("获取数据失败: ${response.code()}"))
                }
            } catch (e: Exception) {
                // 网络异常时返回缓存数据
                cachedData?.let { Result.success(it) }
                    ?: Result.failure(e)
            }
        }
    }
    
    /**
     * 根据设备ID获取班牌数据
     * 
     * @param deviceId 设备ID
     * @return 班牌数据结果
     */
    suspend fun getClassBoardDataByDevice(deviceId: String): Result<ClassBoardData> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getClassBoardDataByDevice(deviceId)
                if (response.isSuccessful && response.body() != null) {
                    cachedData = response.body()
                    Result.success(cachedData!!)
                } else {
                    cachedData?.let { Result.success(it) }
                        ?: Result.failure(Exception("获取数据失败: ${response.code()}"))
                }
            } catch (e: Exception) {
                cachedData?.let { Result.success(it) }
                    ?: Result.failure(e)
            }
        }
    }
    
    /**
     * 获取缓存数据
     */
    fun getCachedData(): ClassBoardData? = cachedData
}
