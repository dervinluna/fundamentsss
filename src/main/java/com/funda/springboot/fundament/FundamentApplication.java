package com.funda.springboot.fundament;

import com.funda.springboot.fundament.bean.MyBean;
import com.funda.springboot.fundament.bean.MyBeanWithDependency;
import com.funda.springboot.fundament.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentApplication implements CommandLineRunner {
	//inyeccion de dependencias inicio
	private ComponentDependency componentDependency;
	//inyectamos nuestra propia dependencia
	private MyBean  myBean;
	private MyBeanWithDependency myBeanWithDependency;




	public FundamentApplication(@Qualifier("componentImplement") ComponentDependency componentDependency, MyBean myBean,
								MyBeanWithDependency myBeanWithDependency){
		this.componentDependency = componentDependency;
		//inyectamos nuestra dependencia
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}
	//inyeccion de dependencias final
	public static void main(String[] args) {

		SpringApplication.run(FundamentApplication.class, args);
	}
//implementacion dentro de oteo objero
	@Override
	public void run(String... args) throws Exception {
		//implementacion
      componentDependency.saludar();
	  myBean.print();
	  myBeanWithDependency.printWithDependency();
	}
}
