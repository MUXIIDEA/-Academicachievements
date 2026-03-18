<template>
  <el-container class="layout-container">
    <!-- 侧边栏 -->
    <el-aside :width="isCollapse ? '64px' : '220px'" class="layout-aside">
      <div class="logo">
        <img src="@/assets/logo.svg" alt="logo" />
        <span v-show="!isCollapse">校园班牌系统</span>
      </div>
      
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :unique-opened="true"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        router
      >
        <template v-for="route in menuRoutes" :key="route.path">
          <!-- 有子菜单 -->
          <el-sub-menu v-if="route.children && route.children.length > 0" :index="route.path">
            <template #title>
              <el-icon><component :is="route.meta?.icon" /></el-icon>
              <span>{{ route.meta?.title }}</span>
            </template>
            
            <el-menu-item
              v-for="child in route.children"
              :key="child.path"
              :index="`${route.path}/${child.path}`"
            >
              <el-icon><component :is="child.meta?.icon" /></el-icon>
              <span>{{ child.meta?.title }}</span>
            </el-menu-item>
          </el-sub-menu>
          
          <!-- 无子菜单 -->
          <el-menu-item v-else :index="route.path">
            <el-icon><component :is="route.meta?.icon" /></el-icon>
            <span>{{ route.meta?.title }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-aside>
    
    <!-- 右侧主体 -->
    <el-container class="layout-main">
      <!-- 头部 -->
      <el-header class="layout-header">
        <div class="header-left">
          <el-icon class="collapse-btn" @click="toggleCollapse">
            <component :is="isCollapse ? 'Expand' : 'Fold'" />
          </el-icon>
          
          <el-breadcrumb separator="/">
            <el-breadcrumb-item v-for="item in breadcrumbs" :key="item.path">
              {{ item.title }}
            </el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        
        <div class="header-right">
          <el-dropdown @command="handleCommand">
            <span class="user-info">
              <el-avatar :size="32" icon="UserFilled" />
              <span class="username">{{ username }}</span>
              <el-icon><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile">个人中心</el-dropdown-item>
                <el-dropdown-item command="password">修改密码</el-dropdown-item>
                <el-dropdown-item divided command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      
      <!-- 内容区 -->
      <el-main class="layout-content">
        <router-view v-slot="{ Component }">
          <transition name="fade" mode="out-in">
            <component :is="Component" />
          </transition>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
/**
 * 后台管理布局组件
 * 
 * 功能说明：
 * - 左侧菜单导航
 * - 顶部面包屑
 * - 用户信息展示
 * - 菜单折叠功能
 * 
 * @author School System
 * @version 1.0.0
 */
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const isCollapse = ref(false)
const username = ref(localStorage.getItem('username') || '管理员')

const menuRoutes = computed(() => {
  const routes = router.options.routes.find(r => r.path === '/')
  return routes?.children?.filter(r => r.meta?.title) || []
})

const activeMenu = computed(() => route.path)

const breadcrumbs = computed(() => {
  const matched = route.matched.filter(item => item.meta?.title)
  return matched.map(item => ({
    path: item.path,
    title: item.meta.title
  }))
})

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}

const handleCommand = (command) => {
  switch (command) {
    case 'profile':
      // TODO: 跳转个人中心
      break
    case 'password':
      // TODO: 打开修改密码对话框
      break
    case 'logout':
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      router.push('/login')
      break
  }
}
</script>

<style lang="scss" scoped>
.layout-container {
  width: 100%;
  height: 100vh;
}

.layout-aside {
  background-color: #304156;
  transition: width 0.3s;
  
  .logo {
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #2b3a4d;
    
    img {
      width: 32px;
      height: 32px;
    }
    
    span {
      margin-left: 10px;
      font-size: 16px;
      font-weight: bold;
      color: #fff;
      white-space: nowrap;
    }
  }
  
  .el-menu {
    border-right: none;
  }
}

.layout-main {
  display: flex;
  flex-direction: column;
}

.layout-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  padding: 0 20px;
  
  .header-left {
    display: flex;
    align-items: center;
    
    .collapse-btn {
      font-size: 20px;
      cursor: pointer;
      margin-right: 15px;
      
      &:hover {
        color: #409EFF;
      }
    }
  }
  
  .header-right {
    .user-info {
      display: flex;
      align-items: center;
      cursor: pointer;
      
      .username {
        margin: 0 8px;
      }
    }
  }
}

.layout-content {
  background-color: #f5f5f5;
  padding: 20px;
  overflow-y: auto;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
