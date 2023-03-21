package com.funda.springboot.fundament.configuration;

import com.funda.springboot.fundament.bean.MyBeanWithProperties;
import com.funda.springboot.fundament.bean.MyBeanWithPropertiesImplement;
import com.funda.springboot.fundament.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    @Value("${value.name}")//instanciamos los valores creados en applicationProperties
    private String name;
    @Value("${value.apellido}")
    private String apellido;
    @Value("${value.random}")
    public String random;
    //ahora vamos a llamar a un bean para usarlas
    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name, apellido);
    }

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }


}
