package com.weilc.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @Description 数据库连接池配置
 * @ClassName DruidConfig
 * @Author weilc
 * @Date 2020-11-24
 * @Version 1.0
 */
@Data
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DruidConfig {
    @Value("${database.driverClassName}")
    private String driver;
    @Value("${database.url}")
    private String url;
    @Value("${database.user}")
    private String user;
    @Value("${database.pwd}")
    private String password;


    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() throws Exception {
        DruidDataSource druid = new DruidDataSource();
        druid.setDriverClassName(driver);
        druid.setUrl(url);
        druid.setUsername(user);
        druid.setPassword(password);
        return druid;
    }

    @Bean(name = "druidWebStatFilter")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略资源
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }
}
