package com.bugmuxi.academicachievements.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bugmuxi.academicachievements.data.model.ClassBoardData
import com.bugmuxi.academicachievements.ui.components.*
import com.bugmuxi.academicachievements.ui.viewmodel.ClassBoardViewModel

/**
 * 班牌主屏幕
 * 
 * 功能说明：
 * - 展示班牌所有信息
 * - 自动刷新数据
 * - 处理加载和错误状态
 * 
 * @author School System
 * @version 1.0.0
 */
@Composable
fun ClassBoardScreen(
    viewModel: ClassBoardViewModel
) {
    val classBoardData by viewModel.classBoardData.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    
    // 显示错误提示
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            // 可以在这里显示Toast或Snackbar
        }
    }
    
    // 主布局
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 顶部信息栏
        TopInfoBar(
            roomInfo = classBoardData?.roomInfo,
            classInfo = classBoardData?.classInfo,
            temperature = classBoardData?.weatherInfo?.temperature ?: 0.0,
            weather = classBoardData?.weatherInfo?.weather ?: "",
            currentDate = classBoardData?.currentDate
        )
        
        // 主内容区域
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 左侧：课程表
            ScheduleCard(
                lessons = classBoardData?.todaySchedule ?: emptyList(),
                currentLesson = classBoardData?.currentLesson,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
            )
            
            // 右侧：当前课程和通知
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 当前/下节课程
                CurrentLessonCard(
                    currentLesson = classBoardData?.currentLesson,
                    nextLesson = classBoardData?.nextLesson,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                
                // 通知公告
                NoticeCard(
                    notices = classBoardData?.notices ?: emptyList(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    }
    
    // 加载指示器
    if (isLoading && classBoardData == null) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(androidx.compose.ui.Alignment.Center)
            )
        }
    }
}
