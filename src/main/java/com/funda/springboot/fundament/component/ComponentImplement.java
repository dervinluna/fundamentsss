package com.funda.springboot.fundament.component;

import org.springframework.stereotype.Component;
//cramos la dependendcia component
@Component
public class ComponentImplement implements ComponentDependency {


    @Override
    public void saludar() {
        System.out.println("Hola Mundo Desde MI componente");

    }

}
