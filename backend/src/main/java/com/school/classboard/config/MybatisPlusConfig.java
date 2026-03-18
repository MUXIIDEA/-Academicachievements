package com.school.classboard.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;

/**
 * MyBatis Plus配置类
 * 
 * 功能说明：
 * - 配置分页插件
 * - 配置自动填充处理器
 * 
 * @author School System
 * @version 1.0.0
 */
@Configuration
public class MybatisPlusConfig {
    
    /**
     * 分页插件配置
     * 
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        
        // 添加分页插件
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.H2);
        paginationInterceptor.setMaxLimit(500L);
        paginationInterceptor.setOverflow(false);
        
        interceptor.addInnerInterceptor(paginationInterceptor);
        
        return interceptor;
    }
    
    /**
     * 自动填充处理器
     * 
     * @return MetaObjectHandler
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            /**
             * 插入时自动填充
             */
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }
            
            /**
             * 更新时自动填充
             */
            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }
        };
    }
}
