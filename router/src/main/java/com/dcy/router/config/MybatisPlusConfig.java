package com.dcy.router.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author：dcy
 * @Description: mybatis-plus 配置类
 * @Date: 2019/9/6 10:43
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 下划线转骆峰
     *
     * @return
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        // 函数式编程
        return (configuration) -> {
            // 使用mybatis-plus 内置的
            configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
        };
    }
}
