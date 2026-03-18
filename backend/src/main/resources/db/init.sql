-- ========================================
-- 校园班牌系统数据库初始化脚本
-- 数据库: MySQL 8.0+
-- 作者: School System
-- 版本: 1.0.0
-- ========================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS classboard DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE classboard;

-- ========================================
-- 系统管理模块表
-- ========================================

-- 部门表
DROP TABLE IF EXISTS sys_dept;
CREATE TABLE sys_dept (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '部门ID',
    dept_name VARCHAR(50) NOT NULL COMMENT '部门名称',
    parent_id BIGINT DEFAULT 0 COMMENT '父部门ID',
    ancestors VARCHAR(255) DEFAULT '' COMMENT '祖级列表',
    order_num INT DEFAULT 0 COMMENT '显示顺序',
    leader VARCHAR(20) DEFAULT '' COMMENT '负责人',
    phone VARCHAR(11) DEFAULT '' COMMENT '联系电话',
    status TINYINT DEFAULT 1 COMMENT '部门状态(0停用 1正常)',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志(0存在 1删除)',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_parent_id (parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 用户表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户账号',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) NOT NULL COMMENT '用户姓名',
    dept_id BIGINT DEFAULT NULL COMMENT '部门ID',
    phone VARCHAR(11) DEFAULT '' COMMENT '手机号码',
    email VARCHAR(50) DEFAULT '' COMMENT '邮箱',
    status TINYINT DEFAULT 1 COMMENT '帐号状态(0停用 1正常)',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志(0存在 1删除)',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_dept_id (dept_id),
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '角色ID',
    role_name VARCHAR(30) NOT NULL COMMENT '角色名称',
    role_key VARCHAR(30) NOT NULL COMMENT '角色权限字符串',
    order_num INT DEFAULT 0 COMMENT '显示顺序',
    status TINYINT DEFAULT 1 COMMENT '角色状态(0停用 1正常)',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志(0存在 1删除)',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 用户角色关联表
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
    user_id BIGINT NOT NULL COMMENT '用户ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY(user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ========================================
-- 教务管理模块表
-- ========================================

-- 教室表
DROP TABLE IF EXISTS edu_room;
CREATE TABLE edu_room (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '教室ID',
    room_code VARCHAR(20) NOT NULL COMMENT '教室编号',
    room_name VARCHAR(50) NOT NULL COMMENT '教室名称',
    building VARCHAR(50) DEFAULT '' COMMENT '楼栋',
    floor INT DEFAULT 1 COMMENT '楼层',
    capacity INT DEFAULT 50 COMMENT '容纳人数',
    device_id VARCHAR(50) DEFAULT '' COMMENT '设备编号',
    city VARCHAR(50) DEFAULT '北京' COMMENT '所在城市',
    status TINYINT DEFAULT 1 COMMENT '状态(0停用 1正常)',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志(0存在 1删除)',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE INDEX idx_device_id (device_id),
    INDEX idx_room_code (room_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教室表';

-- 班级表
DROP TABLE IF EXISTS edu_class;
CREATE TABLE edu_class (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '班级ID',
    class_name VARCHAR(50) NOT NULL COMMENT '班级名称',
    class_code VARCHAR(20) NOT NULL COMMENT '班级编码',
    grade VARCHAR(20) NOT NULL COMMENT '年级',
    head_teacher_id BIGINT DEFAULT NULL COMMENT '班主任ID',
    head_teacher_name VARCHAR(50) DEFAULT '' COMMENT '班主任姓名',
    room_id BIGINT DEFAULT NULL COMMENT '绑定教室ID',
    slogan VARCHAR(200) DEFAULT '' COMMENT '班级口号',
    student_count INT DEFAULT 0 COMMENT '学生人数',
    status TINYINT DEFAULT 1 COMMENT '状态(0停用 1正常)',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志(0存在 1删除)',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE INDEX idx_class_code (class_code),
    INDEX idx_room_id (room_id),
    INDEX idx_grade (grade)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

-- 课程表
DROP TABLE IF EXISTS edu_schedule;
CREATE TABLE edu_schedule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID',
    class_id BIGINT NOT NULL COMMENT '班级ID',
    week_day TINYINT NOT NULL COMMENT '星期几(1-7)',
    lesson_order TINYINT NOT NULL COMMENT '第几节课',
    course_name VARCHAR(50) NOT NULL COMMENT '课程名称',
    teacher_id BIGINT DEFAULT NULL COMMENT '教师ID',
    teacher_name VARCHAR(50) DEFAULT '' COMMENT '教师姓名',
    start_time TIME NOT NULL COMMENT '开始时间',
    end_time TIME NOT NULL COMMENT '结束时间',
    room_id BIGINT DEFAULT NULL COMMENT '教室ID',
    semester VARCHAR(20) DEFAULT '' COMMENT '学期',
    remark VARCHAR(200) DEFAULT '' COMMENT '备注',
    status TINYINT DEFAULT 1 COMMENT '状态(0停用 1正常)',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志(0存在 1删除)',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_class_week (class_id, week_day),
    INDEX idx_semester (semester)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

-- 通知公告表
DROP TABLE IF EXISTS edu_notice;
CREATE TABLE edu_notice (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '通知ID',
    title VARCHAR(100) NOT NULL COMMENT '通知标题',
    content TEXT COMMENT '通知内容',
    notice_type TINYINT DEFAULT 1 COMMENT '通知类型(1学校 2年级 3班级)',
    scope VARCHAR(500) DEFAULT '0' COMMENT '发布范围(0全校,具体班级ID)',
    publisher_id BIGINT DEFAULT NULL COMMENT '发布人ID',
    publisher_name VARCHAR(50) DEFAULT '' COMMENT '发布人姓名',
    publish_time DATETIME DEFAULT NULL COMMENT '发布时间',
    effect_start_time DATETIME DEFAULT NULL COMMENT '生效开始时间',
    effect_end_time DATETIME DEFAULT NULL COMMENT '生效结束时间',
    is_top TINYINT DEFAULT 0 COMMENT '是否置顶(0否 1是)',
    view_count INT DEFAULT 0 COMMENT '浏览次数',
    status TINYINT DEFAULT 0 COMMENT '状态(0草稿 1已发布 2已撤回)',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志(0存在 1删除)',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_status (status),
    INDEX idx_publish_time (publish_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

-- ========================================
-- 初始化数据
-- ========================================

-- 初始化部门数据
INSERT INTO sys_dept (dept_name, parent_id, ancestors, order_num, leader, phone) VALUES
('学校总部', 0, '0', 1, '张校长', '13800138000'),
('教务处', 1, '0,1', 1, '李主任', '13800138001'),
('学生处', 1, '0,1', 2, '王主任', '13800138002'),
('高一年级组', 2, '0,1,2', 1, '赵组长', '13800138003'),
('高二年级组', 2, '0,1,2', 2, '钱组长', '13800138004'),
('高三年级组', 2, '0,1,2', 3, '孙组长', '13800138005');

-- 初始化用户数据 (密码: 123456, BCrypt加密)
INSERT INTO sys_user (username, password, real_name, dept_id, phone, email) VALUES
('admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '系统管理员', 1, '13800138000', 'admin@school.edu.cn'),
('teacher1', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '张老师', 4, '13800138010', 'zhang@school.edu.cn'),
('teacher2', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '李老师', 4, '13800138011', 'li@school.edu.cn'),
('teacher3', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '王老师', 5, '13800138012', 'wang@school.edu.cn');

-- 初始化角色数据
INSERT INTO sys_role (role_name, role_key, order_num) VALUES
('超级管理员', 'admin', 1),
('教务管理员', 'academic', 2),
('班主任', 'headteacher', 3),
('普通教师', 'teacher', 4);

-- 初始化用户角色关联
INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1),
(2, 3),
(3, 4),
(4, 3);

-- 初始化教室数据
INSERT INTO edu_room (room_code, room_name, building, floor, capacity, device_id, city) VALUES
('101', '高一(1)班教室', '教学楼A', 1, 50, 'DEVICE001', '北京'),
('102', '高一(2)班教室', '教学楼A', 1, 50, 'DEVICE002', '北京'),
('201', '高一(3)班教室', '教学楼A', 2, 50, 'DEVICE003', '北京'),
('202', '高二(1)班教室', '教学楼A', 2, 50, 'DEVICE004', '北京'),
('301', '高三(1)班教室', '教学楼A', 3, 50, 'DEVICE005', '北京');

-- 初始化班级数据
INSERT INTO edu_class (class_name, class_code, grade, head_teacher_id, head_teacher_name, room_id, slogan, student_count) VALUES
('高一(1)班', 'G1-01', '高一', 2, '张老师', 1, '勤奋学习，追求卓越', 45),
('高一(2)班', 'G1-02', '高一', 3, '李老师', 2, '团结奋进，勇攀高峰', 46),
('高一(3)班', 'G1-03', '高一', NULL, '', 3, '博学笃志，切问近思', 44),
('高二(1)班', 'G2-01', '高二', 4, '王老师', 4, '自强不息，厚德载物', 48),
('高三(1)班', 'G3-01', '高三', NULL, '', 5, '志存高远，脚踏实地', 47);

-- 初始化课程表数据 (高一(1)班示例)
INSERT INTO edu_schedule (class_id, week_day, lesson_order, course_name, teacher_name, start_time, end_time, semester) VALUES
-- 周一
(1, 1, 1, '语文', '张老师', '08:00:00', '08:45:00', '2024-2025-1'),
(1, 1, 2, '数学', '李老师', '08:55:00', '09:40:00', '2024-2025-1'),
(1, 1, 3, '英语', '王老师', '10:00:00', '10:45:00', '2024-2025-1'),
(1, 1, 4, '物理', '赵老师', '10:55:00', '11:40:00', '2024-2025-1'),
(1, 1, 5, '化学', '钱老师', '14:00:00', '14:45:00', '2024-2025-1'),
(1, 1, 6, '生物', '孙老师', '14:55:00', '15:40:00', '2024-2025-1'),
(1, 1, 7, '体育', '周老师', '15:50:00', '16:35:00', '2024-2025-1'),
-- 周二
(1, 2, 1, '数学', '李老师', '08:00:00', '08:45:00', '2024-2025-1'),
(1, 2, 2, '语文', '张老师', '08:55:00', '09:40:00', '2024-2025-1'),
(1, 2, 3, '英语', '王老师', '10:00:00', '10:45:00', '2024-2025-1'),
(1, 2, 4, '历史', '吴老师', '10:55:00', '11:40:00', '2024-2025-1'),
(1, 2, 5, '地理', '郑老师', '14:00:00', '14:45:00', '2024-2025-1'),
(1, 2, 6, '政治', '冯老师', '14:55:00', '15:40:00', '2024-2025-1'),
(1, 2, 7, '自习', '', '15:50:00', '16:35:00', '2024-2025-1');

-- 初始化通知公告数据
INSERT INTO edu_notice (title, content, notice_type, scope, publisher_id, publisher_name, publish_time, effect_start_time, effect_end_time, is_top, status) VALUES
('关于期中考试安排的通知', '<p>各位同学：</p><p>期中考试将于下周一至周三进行，请同学们做好准备。</p>', 1, '0', 1, '系统管理员', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 1),
('校运动会报名通知', '<p>一年一度的校运动会即将举行，请各班级积极报名参加。</p>', 1, '0', 1, '系统管理员', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY), 0, 1),
('高一(1)班班会通知', '<p>本周五下午召开班会，请全体同学准时参加。</p>', 3, '1', 2, '张老师', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 0, 1);

-- 查询验证
SELECT '数据库初始化完成！' AS message;
