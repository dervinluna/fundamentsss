package com.funda.springboot.fundament.bean;

public class MyBeanImplement implements  MyBean{
    @Override
    public void print() {
        System.out.println("Hola esta es mi implementacion propia del Bean");
    }
}
