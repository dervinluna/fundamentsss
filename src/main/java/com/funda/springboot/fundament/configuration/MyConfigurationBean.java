package com.funda.springboot.fundament.configuration;

import com.funda.springboot.fundament.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
      return new MyBean2Implement();
    }
    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }
    @Bean
    public MyBeanWithDependency beanOperationOperationWhithDependency(MyOperation myOperation){
        return new MyBeanDependenceImplement(myOperation);
    }

}
