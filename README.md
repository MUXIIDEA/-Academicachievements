# 校园班牌系统

## 项目概述

校园班牌系统是一个完整的校园信息化解决方案，包含后台管理系统、前端管理界面和Android一体机展示端。

### 系统架构

```
┌─────────────────────────────────────────────────────────────┐
│                        校园班牌系统                           │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │  后端服务     │  │  管理后台     │  │  Android APP │     │
│  │ Spring Boot  │  │   Vue.js     │  │   Compose    │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│         │                  │                  │             │
│         └──────────────────┼──────────────────┘             │
│                            │                                │
│                    ┌───────┴───────┐                       │
│                    │   MySQL/H2    │                       │
│                    │    数据库      │                       │
│                    └───────────────┘                       │
└─────────────────────────────────────────────────────────────┘
```

## 项目结构

```
Academicachievements/
├── backend/                    # 后端服务
│   ├── src/main/java/
│   │   └── com/school/classboard/
│   │       ├── ClassBoardApplication.java    # 启动类
│   │       ├── config/        # 配置类
│   │       ├── controller/    # 控制器
│   │       ├── service/       # 服务层
│   │       ├── mapper/        # 数据访问层
│   │       ├── entity/        # 实体类
│   │       ├── dto/           # 数据传输对象
│   │       └── common/        # 公共类
│   ├── src/main/resources/
│   │   ├── application.yml    # 配置文件
│   │   └── db/init.sql        # 数据库初始化脚本
│   └── pom.xml                # Maven配置
│
├── backend/admin-web/          # 前端管理界面
│   ├── src/
│   │   ├── api/               # API接口
│   │   ├── components/        # 组件
│   │   ├── views/             # 页面
│   │   ├── router/            # 路由
│   │   ├── store/             # 状态管理
│   │   └── utils/             # 工具类
│   ├── package.json
│   └── vite.config.js
│
└── app/                        # Android应用
    ├── src/main/java/com/bugmuxi/academicachievements/
    │   ├── MainActivity.kt    # 主Activity
    │   ├── data/              # 数据层
    │   │   ├── api/           # API服务
    │   │   ├── model/         # 数据模型
    │   │   └── repository/    # 数据仓库
    │   ├── ui/                # UI层
    │   │   ├── components/    # UI组件
    │   │   ├── screens/       # 屏幕
    │   │   └── viewmodel/     # ViewModel
    │   └── receiver/          # 广播接收器
    └── build.gradle.kts
```

## 技术栈

### 后端服务
- **框架**: Spring Boot 3.2.0
- **ORM**: MyBatis Plus 3.5.5
- **数据库**: MySQL 8.0+ / H2 (开发环境)
- **安全**: Spring Security + JWT
- **文档**: SpringDoc OpenAPI 3
- **工具**: Hutool, Lombok, EasyExcel

### 前端管理界面
- **框架**: Vue 3.4
- **构建工具**: Vite 5.0
- **UI组件**: Element Plus 2.4
- **状态管理**: Pinia 2.1
- **路由**: Vue Router 4.2
- **HTTP客户端**: Axios 1.6

### Android应用
- **语言**: Kotlin
- **UI框架**: Jetpack Compose
- **架构**: MVVM
- **网络**: Retrofit 2.9
- **图片加载**: Coil 2.5
- **数据存储**: DataStore

## 功能模块

### 1. 用户管理
- 用户列表查询（分页）
- 用户新增、修改、删除
- 密码管理（修改、重置）
- 用户状态管理（启用/禁用）
- 角色分配

### 2. 班牌展示
- 实时日期时间显示
- 天气信息展示
- 教室和班级信息
- 今日课表展示
- 当前/下节课程高亮
- 通知公告滚动

### 3. 教务管理
- 班级管理
- 教室管理
- 课表管理
- 通知公告管理

## 快速开始

### 后端服务

1. **配置数据库**
```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/classboard
    username: root
    password: your_password
```

2. **初始化数据库**
```bash
mysql -u root -p < backend/src/main/resources/db/init.sql
```

3. **启动服务**
```bash
cd backend
mvn spring-boot:run
```

访问: http://localhost:8080/api
API文档: http://localhost:8080/api/swagger-ui.html

### 前端管理界面

1. **安装依赖**
```bash
cd backend/admin-web
npm install
```

2. **启动开发服务器**
```bash
npm run dev
```

访问: http://localhost:3000

默认账号: admin / 123456

### Android应用

1. **配置API地址**
```kotlin
// RetrofitClient.kt
private const val BASE_URL = "http://your-server-ip:8080/api/"
```

2. **编译安装**
使用Android Studio打开项目，编译并安装到设备

## API接口文档

### 用户管理

| 接口 | 方法 | 说明 |
|------|------|------|
| /api/system/user/list | GET | 获取用户列表 |
| /api/system/user/{id} | GET | 获取用户详情 |
| /api/system/user | POST | 新增用户 |
| /api/system/user/{id} | PUT | 修改用户 |
| /api/system/user/{id} | DELETE | 删除用户 |
| /api/system/user/{id}/resetPassword | PUT | 重置密码 |
| /api/system/user/{id}/status | PUT | 修改状态 |

### 班牌展示

| 接口 | 方法 | 说明 |
|------|------|------|
| /api/classboard/data | GET | 获取班牌数据(按教室ID) |
| /api/classboard/data/device/{deviceId} | GET | 获取班牌数据(按设备ID) |
| /api/classboard/schedule/today | GET | 获取今日课表 |
| /api/classboard/schedule/current | GET | 获取当前课程 |
| /api/classboard/schedule/next | GET | 获取下一节课 |
| /api/classboard/notices | GET | 获取通知公告 |

## 部署说明

### 生产环境配置

1. **后端服务**
```yaml
spring:
  datasource:
    url: jdbc:mysql://prod-db:3306/classboard
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  
jwt:
  secret: ${JWT_SECRET}
  expiration: 86400000
```

2. **前端部署**
```bash
npm run build
# 将dist目录部署到Nginx
```

3. **Android应用**
- 修改API地址为生产服务器
- 签名打包APK
- 安装到一体机设备

### 注意事项

1. **安全配置**
- 修改默认密码
- 配置JWT密钥
- 启用HTTPS

2. **性能优化**
- 配置数据库连接池
- 启用Redis缓存
- 配置CDN加速

3. **监控告警**
- 配置日志收集
- 设置性能监控
- 配置异常告警

## 开发团队

- **项目名称**: 校园班牌系统
- **版本**: V1.0.0
- **开发人员**: TheBUGMUXI

## 许可证

本项目仅供学习和研究使用。
