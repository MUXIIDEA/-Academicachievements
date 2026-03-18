<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <!-- 统计卡片 -->
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-icon" style="background-color: #409EFF">
            <el-icon><User /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.userCount }}</div>
            <div class="stat-label">用户总数</div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-icon" style="background-color: #67C23A">
            <el-icon><School /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.classCount }}</div>
            <div class="stat-label">班级总数</div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-icon" style="background-color: #E6A23C">
            <el-icon><Location /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.roomCount }}</div>
            <div class="stat-label">教室总数</div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-icon" style="background-color: #F56C6C">
            <el-icon><Bell /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.noticeCount }}</div>
            <div class="stat-label">通知公告</div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    
    <!-- 快捷操作 -->
    <el-card class="quick-actions">
      <template #header>
        <span>快捷操作</span>
      </template>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-button type="primary" class="action-btn" @click="$router.push('/system/user')">
            <el-icon><User /></el-icon>
            用户管理
          </el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="success" class="action-btn" @click="$router.push('/education/class')">
            <el-icon><School /></el-icon>
            班级管理
          </el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" class="action-btn" @click="$router.push('/education/schedule')">
            <el-icon><Calendar /></el-icon>
            课表管理
          </el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="danger" class="action-btn" @click="$router.push('/notice')">
            <el-icon><Bell /></el-icon>
            发布通知
          </el-button>
        </el-col>
      </el-row>
    </el-card>
    
    <!-- 系统信息 -->
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>系统信息</span>
          </template>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="系统名称">校园班牌管理系统</el-descriptions-item>
            <el-descriptions-item label="系统版本">V1.0.0</el-descriptions-item>
            <el-descriptions-item label="开发团队">School System</el-descriptions-item>
            <el-descriptions-item label="技术栈">Spring Boot + Vue3 + Android</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
      
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>最近通知</span>
          </template>
          <el-timeline>
            <el-timeline-item
              v-for="notice in recentNotices"
              :key="notice.id"
              :timestamp="notice.time"
              placement="top"
            >
              {{ notice.title }}
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
/**
 * 首页仪表盘
 * 
 * @author School System
 * @version 1.0.0
 */
import { ref, onMounted } from 'vue'

const stats = ref({
  userCount: 156,
  classCount: 24,
  roomCount: 30,
  noticeCount: 12
})

const recentNotices = ref([
  { id: 1, title: '关于期中考试安排的通知', time: '2024-01-15 10:30' },
  { id: 2, title: '校运动会报名通知', time: '2024-01-14 15:20' },
  { id: 3, title: '寒假放假通知', time: '2024-01-13 09:00' }
])

onMounted(() => {
  // TODO: 加载统计数据
})
</script>

<style lang="scss" scoped>
.dashboard-container {
  .stat-card {
    display: flex;
    align-items: center;
    padding: 20px;
    
    .stat-icon {
      width: 60px;
      height: 60px;
      border-radius: 10px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 20px;
      
      .el-icon {
        font-size: 30px;
        color: #fff;
      }
    }
    
    .stat-info {
      .stat-value {
        font-size: 28px;
        font-weight: bold;
        color: #333;
      }
      
      .stat-label {
        font-size: 14px;
        color: #999;
        margin-top: 5px;
      }
    }
  }
  
  .quick-actions {
    margin: 20px 0;
    
    .action-btn {
      width: 100%;
      height: 60px;
      font-size: 16px;
      
      .el-icon {
        margin-right: 8px;
      }
    }
  }
}
</style>
