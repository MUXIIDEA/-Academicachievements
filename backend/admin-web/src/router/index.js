/**
 * Vue Router路由配置
 * 
 * 功能说明：
 * - 定义应用路由
 * - 配置路由守卫
 * - 配置页面布局
 * 
 * @author School System
 * @version 1.0.0
 */
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: () => import('@/layout/index.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index.vue'),
        meta: { title: '首页', icon: 'HomeFilled' }
      },
      {
        path: 'system',
        name: 'System',
        meta: { title: '系统管理', icon: 'Setting' },
        children: [
          {
            path: 'user',
            name: 'User',
            component: () => import('@/views/system/user/index.vue'),
            meta: { title: '用户管理', icon: 'User' }
          },
          {
            path: 'dept',
            name: 'Dept',
            component: () => import('@/views/system/dept/index.vue'),
            meta: { title: '部门管理', icon: 'OfficeBuilding' }
          },
          {
            path: 'role',
            name: 'Role',
            component: () => import('@/views/system/role/index.vue'),
            meta: { title: '角色管理', icon: 'UserFilled' }
          }
        ]
      },
      {
        path: 'education',
        name: 'Education',
        meta: { title: '教务管理', icon: 'Reading' },
        children: [
          {
            path: 'class',
            name: 'Class',
            component: () => import('@/views/education/class/index.vue'),
            meta: { title: '班级管理', icon: 'School' }
          },
          {
            path: 'room',
            name: 'Room',
            component: () => import('@/views/education/room/index.vue'),
            meta: { title: '教室管理', icon: 'Location' }
          },
          {
            path: 'schedule',
            name: 'Schedule',
            component: () => import('@/views/education/schedule/index.vue'),
            meta: { title: '课表管理', icon: 'Calendar' }
          }
        ]
      },
      {
        path: 'notice',
        name: 'Notice',
        component: () => import('@/views/notice/index.vue'),
        meta: { title: '通知公告', icon: 'Bell' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

/**
 * 路由前置守卫
 */
router.beforeEach((to, from, next) => {
  document.title = to.meta.title ? `${to.meta.title} - 校园班牌管理系统` : '校园班牌管理系统'
  
  const token = localStorage.getItem('token')
  
  if (to.path !== '/login' && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router
