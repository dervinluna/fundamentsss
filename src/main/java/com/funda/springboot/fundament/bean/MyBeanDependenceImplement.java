package com.funda.springboot.fundament.bean;

public class MyBeanDependenceImplement implements MyBeanWithDependency{
    //inyectamos nuestra dependencia myOperation
    private MyOperation myOperation;//llamamos una dependencia

    public MyBeanDependenceImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int numero=1;
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
