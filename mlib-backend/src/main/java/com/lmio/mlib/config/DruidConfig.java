/*
 * @Author: lmio
 * @Date: 2023-04-04 17:21:21
 * @LastEditTime: 2023-04-04 17:40:57
 * @FilePath: /mlib/src/main/java/com/lmio/mlib/config/DruidConfig.java
 * @Description: 
 */
package com.lmio.mlib.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}