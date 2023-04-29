package com.lmio.mlib.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午7:36 2023/4/29
 * @Modified By:lmio
 */

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Resource
    private DataSource dataSource;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
