/**
 * Vue应用入口文件
 * 
 * 功能说明：
 * - 创建Vue应用实例
 * - 配置全局插件(Element Plus, Router, Pinia)
 * - 挂载应用
 * 
 * @author School System
 * @version 1.0.0
 */
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import App from './App.vue'
import router from './router'
import './styles/index.scss'

const app = createApp(App)

// 注册Element Plus图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// 使用插件
app.use(createPinia())
app.use(router)
app.use(ElementPlus, {
  locale: zhCn
})

// 挂载应用
app.mount('#app')
