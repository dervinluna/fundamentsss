package com.funda.springboot.fundament.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency {
    @Override
    public void saludar() {
        System.out.println("hello word desde mi componente 2");
    }
}
