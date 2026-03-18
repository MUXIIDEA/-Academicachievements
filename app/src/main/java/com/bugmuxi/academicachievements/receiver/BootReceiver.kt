package com.bugmuxi.academicachievements.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bugmuxi.academicachievements.MainActivity

/**
 * 开机自启动广播接收器
 * 
 * 功能说明：
 * - 监听系统开机广播
 * - 自动启动班牌应用
 * 
 * @author School System
 * @version 1.0.0
 */
class BootReceiver : BroadcastReceiver() {
    
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // 启动主Activity
            val launchIntent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(launchIntent)
        }
    }
}
