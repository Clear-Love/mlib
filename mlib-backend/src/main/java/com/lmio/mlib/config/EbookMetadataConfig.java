package com.lmio.mlib.config;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午4:07 2023/5/13
 * @Modified By:lmio
 */

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EbookMetadataConfig {
    @Bean
    public Tika tika() {
        return new Tika();
    }
}
