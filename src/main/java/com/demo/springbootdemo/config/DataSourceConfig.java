package com.demo.springbootdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author: guan.kai
 * @date: 2019/11/25 13:50
 **/
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    /**
     * 注入druid数据源
     * @return
     */
    @Bean(name = "druidDataSource")
    @Qualifier(value = "druidDataSource")
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    /**
     * 根据druid数据源注入jdbcTemplate
     * @param dataSource
     * @return
     */
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Qualifier(value = "druidDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
