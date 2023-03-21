package com.funda.springboot.fundament.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanDependenceImplement implements MyBeanWithDependency{
    //llamamos a log
    Log LOGGER = LogFactory.getLog(MyBeanDependenceImplement.class);
    //inyectamos nuestra dependencia myOperation
    private MyOperation myOperation;//llamamos una dependencia

    public MyBeanDependenceImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        //imprimimos el nivel log info
        LOGGER.info(" Hemos Ingresado al metodo printWithDependency");
        int numero=1;
        //usamos log debug
        LOGGER.debug("El numero enviado como parametro a la dependencia operation es: "+numero);

        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");

    }
}
