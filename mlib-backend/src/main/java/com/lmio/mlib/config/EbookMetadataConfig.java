package com.lmio.mlib.config;

/**
 * @Author: lmio
 * @Description: TODO
 * @Date: 下午4:07 2023/5/13
 * @Modified By:lmio
 */

import org.apache.tika.Tika;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EbookMetadataConfig {
    @Bean
    public Parser parser() {
        return new AutoDetectParser();
    }

    @Bean
    public Tika tika() {
        return new Tika();
    }
}
