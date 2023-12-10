package com.mmu.product_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.product_app.controllers.CommandLineController;

@SpringBootApplication
@RestController
public class ProductAppApplication {

	public static void main(String[] args) {
		
		var x = SpringApplication.run(ProductAppApplication.class, args);
		final int LAST_OPTION_MENU = 6;
		CommandLineController cliHandler = x.getBean(CommandLineController.class);
		int option;
		do {
			option = cliHandler.showMenu();
			switch (option) {
				case 1 -> // list all products
					cliHandler.getAll();
				case 2 -> // list one product
					cliHandler.getOne();
				case 3 -> // create product
					cliHandler.createProduct();
				case 4 -> // update product
					cliHandler.updateProduct();
				case 5 -> // delete product
					cliHandler.deleteProduct();
				case LAST_OPTION_MENU -> // exit
					System.out.println("Bye!");
				default -> System.out.println("Invalid Option!!");
			}
		} while (option != LAST_OPTION_MENU);
	}

}
