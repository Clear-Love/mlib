/*
 * @Author: lmio
 * @Date: 2023-04-04 17:30:37
 * @LastEditTime: 2023-04-04 19:50:08
 * @FilePath: /mlib/src/main/java/com/lmio/mlib/config/MyBatisConfig.java
 * @Description: mybatis配置
 */
package com.lmio.mlib.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix =  "spring.mybatis")
@MapperScan("com.lmio.mlib.mappers")
public class MyBatisConfig {
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
}