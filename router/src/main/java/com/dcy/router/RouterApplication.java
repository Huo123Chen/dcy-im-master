package com.dcy.router;

import cn.hutool.extra.spring.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/16 8:08
 */
@SpringBootApplication(scanBasePackages = {"com.dcy"})
@MapperScan(basePackages = {"com.dcy.router.mapper"})
public class RouterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class);
    }
}
