package com.funda.springboot.fundament.configuration;

import com.funda.springboot.fundament.bean.MyBeanWithProperties;
import com.funda.springboot.fundament.bean.MyBeanWithPropertiesImplement;
import com.funda.springboot.fundament.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.properties")
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    @Value("${value.name}")//instanciamos los valores creados en applicationProperties
    private String name;
    @Value("${value.apellido}")
    private String apellido;
    @Value("${value.random}")
    public String random;
    //ahora vamos a llamar a un bean para usarlas

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name, apellido);
    }

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }


}
