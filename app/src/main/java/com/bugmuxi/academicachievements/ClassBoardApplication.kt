package com.bugmuxi.academicachievements

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

/**
 * 应用程序入口类
 * 
 * 功能说明：
 * - 初始化应用程序
 * - 创建通知渠道
 * - 配置全局设置
 * 
 * @author School System
 * @version 1.0.0
 */
class ClassBoardApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // 创建通知渠道
        createNotificationChannel()
    }
    
    /**
     * 创建通知渠道
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "班牌通知",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "校园班牌系统通知"
            }
            
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
    
    companion object {
        const val CHANNEL_ID = "classboard_channel"
    }
}
