package com.weilc.config.db;

import com.weilc.config.handler.DbHandler;
import com.weilc.config.handler.DbProcessor;
import com.weilc.dao.BaseGeneratorDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Description
 * @ClassName DbConfig
 * @Author weilc
 * @Date 2020-11-19
 * @Version 1.0
 */
@Configuration
public class DbConfig {

    @Value("${database.type}")
    private String database;
    @Autowired
    private DbProcessor processor;

    @Bean
    @Primary
    public BaseGeneratorDao getGeneratorDao() {
        if (StringUtils.isBlank(database)) {
            database = "mysql";
        }
        DbHandler handler = processor.choose(database);
        return handler.handle();
    }
}
