package com.boot.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@PropertySource(value = {"classpath:dataSource.properties"},encoding="utf-8")
public class DbConfig {

    @Value("${master.datasource.url}")
    private String master_url;
    @Value("${master.datasource.username}")
    private String master_username;
    @Value("${master.datasource.password}")
    private String master_password;
    @Bean
    public DataSource getDataSource(){
        try {
            Properties properties = new Properties();
            properties.setProperty("url", master_url);
            properties.setProperty("username", master_username);
            properties.setProperty("password", master_password);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Bean
    public QueryRunner getQueryRunner(){
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        return queryRunner;
    }
}
