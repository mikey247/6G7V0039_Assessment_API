package com.mmu.product_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.product_app.controllers.CommandLineController;
//./mvnw -e azure-spring-apps:deploy
// mvn package


@SpringBootApplication
@RestController
public class ProductAppApplication {

	public static void main(String[] args) {
		
		var x = SpringApplication.run(ProductAppApplication.class, args);
		final int LAST_OPTION_MENU = 11;
		CommandLineController cliHandler = x.getBean(CommandLineController.class);
		int option;
		do {
			option = cliHandler.showMenu();
			switch (option) {
				case 1 -> // list all products
					cliHandler.getAllProducts();
				case 2 -> // list one product
					cliHandler.getOneProduct();
				case 3 -> // create product
					cliHandler.createProduct();
				case 4 -> // update product
					cliHandler.updateProduct();
				case 5 -> // delete product
					cliHandler.deleteProduct();
				case 6 -> //get all customers
					cliHandler.getAllCustomers();
				case 7 -> // find one customer
					cliHandler.getCustomer();
				case 8 -> // create customer
					cliHandler.createCustomer();
				case 9 -> // update customer
					cliHandler.updateCustomer();
				case 10 -> // delete customer
					cliHandler.deleteCustomer();
				case LAST_OPTION_MENU -> // exit
					System.out.println("Bye!");
				default -> System.out.println("Invaliid Option!!");
			}
		} while (option != LAST_OPTION_MENU);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}	

}
