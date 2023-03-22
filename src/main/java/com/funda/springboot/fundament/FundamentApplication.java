package com.funda.springboot.fundament;

import com.funda.springboot.fundament.bean.MyBean;
import com.funda.springboot.fundament.bean.MyBeanWithDependency;
import com.funda.springboot.fundament.bean.MyBeanWithProperties;
import com.funda.springboot.fundament.component.ComponentDependency;
import com.funda.springboot.fundament.entity.User;
import com.funda.springboot.fundament.pojo.UserPojo;
import com.funda.springboot.fundament.repository.UserRepository;
import com.funda.springboot.fundament.sevice.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentApplication implements CommandLineRunner {
	//llamamos la clase logerr
	private final Log LOGGER = LogFactory.getLog(FundamentApplication.class);
	//inyeccion de dependencias inicio
	private ComponentDependency componentDependency;
	//inyectamos nuestra propia dependencia
	private MyBean  myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;



	public FundamentApplication(@Qualifier("componentImplement") ComponentDependency componentDependency, MyBean myBean,
								MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								UserPojo userPojo, UserRepository userRepository,UserService userService){

		this.componentDependency = componentDependency;
		//inyectamos nuestra dependencia
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	//inyeccion de dependencias final
	public static void main(String[] args) {

		SpringApplication.run(FundamentApplication.class, args);
	}
//implementacion dentro del objeto es como la ejecucion
	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUserInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();

	}
	//metodo
	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1","TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2","TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3","TestTransactional3@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4","TestTransactional4@domain.com", LocalDate.now());
		List<User> users = Arrays.asList(test1,test2,test3,test4);
		userService.saveTransactional(users);
		userService.getAllUsers()
				.stream()
				.forEach(user ->
						LOGGER.info("Este es el usuario dentro del metodo transaccional "+user));

	}
	//metodo pa jpaQuery
	private void  getInformationJpqlFromUser(){
		   /* LOGGER.info("Usuario con el metodo findByUserEmail"+
			userRepository.findByUserEmail("julie@domain.com")
					.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
			userRepository.findAndSort("user", Sort.by("id").descending())
					.stream()
					.forEach(user -> LOGGER.info("Usuario con metodo Sort"+user));

			userRepository.findByName("John")
					.stream()
					.forEach(user -> LOGGER.info("Usuario con query method" + user));
			LOGGER.info("Usuario con Query method findByEmailAndName" + userRepository.findByEmailAndName("daniela@domain.com","Daniela")
					.orElseThrow(()-> new RuntimeException("Usuario no encontrado")));
			userRepository.findByNameLike("%u%")
					.stream()
					.forEach(user -> LOGGER.info("Usuario findByNameLike " + user));
		    userRepository.findByNameOrEmail(null,"user10@domain.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));*/
		userRepository.findByBirthDateBetween(LocalDate.of(2021,3,1),LocalDate.of(2021,4,2))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas: " + user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado " + user));
		LOGGER.info("El Usuario a partir del named parameter es: "+userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021,7,20),
						"daniela@domain.com")
				.orElseThrow(()->
						new RuntimeException("No se encontro el usuario a partir de named Parameter")));
	}

	//metodo para persistir informacion
	private void saveUserInDataBase(){
		User user1 = new User("John", "jonn@domain.com", LocalDate.of(2021,3,20));
		User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021,5,21));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021,7,20));
		User user4 = new User("Dervi", "dervin@domain.com", LocalDate.of(2021,2,26));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021,11,25));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021,4,21));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021,6,22));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021,1,29));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021,12,30));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021,10,10));
		User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021,6,15));
		User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021,3,12));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		//implementacion
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
		//usamos captura de errores
		try {
			//error
			int value = 10/0;
			LOGGER.debug("MI valor:"+ value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividir por cero" + e.getMessage());
		}
	}

}
