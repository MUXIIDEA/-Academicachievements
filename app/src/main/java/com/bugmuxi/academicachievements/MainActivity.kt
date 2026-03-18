package com.bugmuxi.academicachievements

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bugmuxi.academicachievements.ui.screens.ClassBoardScreen
import com.bugmuxi.academicachievements.ui.theme.AcademicAchievementsTheme
import com.bugmuxi.academicachievements.ui.viewmodel.ClassBoardViewModel

/**
 * 主Activity
 * 
 * 功能说明：
 * - 全屏展示班牌内容
 * - 横屏锁定
 * - 保持屏幕常亮
 * - 隐藏系统UI
 * 
 * @author School System
 * @version 1.0.0
 */
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 设置横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        
        // 保持屏幕常亮
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        
        // 隐藏系统UI (全屏模式)
        hideSystemUI()
        
        enableEdgeToEdge()
        
        setContent {
            AcademicAchievementsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: ClassBoardViewModel = viewModel()
                    
                    // 监听生命周期,在恢复时重新隐藏系统UI
                    DisposableEffect(Unit) {
                        onDispose {
                            // 清理资源
                        }
                    }
                    
                    ClassBoardScreen(viewModel = viewModel)
                }
            }
        }
    }
    
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }
    
    /**
     * 隐藏系统UI,实现全屏沉浸式体验
     */
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
    }
}
