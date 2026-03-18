package com.school.classboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 校园班牌系统后端服务主启动类
 * 
 * 功能说明：
 * - 启动Spring Boot应用
 * - 配置MyBatis Mapper扫描
 * - 启用定时任务调度
 * 
 * @author School System
 * @version 1.0.0
 */
@SpringBootApplication
@MapperScan("com.school.classboard.mapper")
@EnableScheduling
public class ClassBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassBoardApplication.class, args);
        System.out.println("========================================");
        System.out.println("   校园班牌系统后端服务启动成功！");
        System.out.println("   API文档地址: http://localhost:8080/swagger-ui.html");
        System.out.println("========================================");
    }
}
