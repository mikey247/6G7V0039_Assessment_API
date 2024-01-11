package com.mmu.product_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.product_app.controllers.CommandLineController;
import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.models.FoodProductItem;
import com.mmu.product_app.services.FoodItemService;
// mvn package
//./mvnw -e azure-spring-apps:deploy
// ./mvnw javadoc:javadoc
import com.mmu.product_app.services.FoodProductService;


@SpringBootApplication
@RestController
public class ProductAppApplication implements CommandLineRunner {

	@Autowired
	private final FoodProductService foodProductService;
	@Autowired
	private final FoodItemService foodItemService;

	public ProductAppApplication(FoodProductService foodProductService, FoodItemService foodItemService) {
		this.foodProductService = foodProductService;
		this.foodItemService = foodItemService;
	}
	
	public static void main(String[] args) {
		var x = SpringApplication.run(ProductAppApplication.class, args);
		FoodProductService foodProductService = x.getBean(FoodProductService.class);
		createFoodProductValues(foodProductService);
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

	
	@Override
	public void run(String... args) throws Exception {
		// Create food product values
		// new FoodProduct("SKU-122", "Bag of Oranges", "Fruit", 100.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-123", "Bag of Bananas", "Fruit", 100.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-124", "5kg of Penne Pasta", "Pasta", 50.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-125", "48 x 330ml of Coca Cola", "Soft Drink", 120.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-126", "5kg of Organic Spaghetti", "Pasta", 50.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-127", "48 x 330ml of Fanta", "Soft Drink", 100.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-128", "Box of Pears", "Fruit", 200.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-129", "Bag of Apples", "Fruit", 150.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-130", "2kg of Basmati Rice", "Rice", 80.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-131", "6-pack of Beer", "Alcohol", 90.0));
		foodProductService.createFoodProduct(new FoodProduct("SKU-132", "Can of Tuna", "Seafood", 70.0));

		foodItemService.createFoodProductItem(new FoodProductItem("12345", "2024-12-31", foodProductService.getFoodProduct(1L)));
		foodItemService.createFoodProductItem(new FoodProductItem("12346", "2023-06-30", foodProductService.getFoodProduct(2L)));
		foodItemService.createFoodProductItem(new FoodProductItem("12347", "2022-09-15", foodProductService.getFoodProduct(3L)));
		foodItemService.createFoodProductItem(new FoodProductItem("12348", "2023-02-28", foodProductService.getFoodProduct(4L)));
		foodItemService.createFoodProductItem(new FoodProductItem("12349", "2023-07-31", foodProductService.getFoodProduct(5L)));
		foodItemService.createFoodProductItem(new FoodProductItem("12350", "2022-11-30", foodProductService.getFoodProduct(6L)));
		foodItemService.createFoodProductItem(new FoodProductItem("12351", "2024-03-15", foodProductService.getFoodProduct(7L)));
		foodItemService.createFoodProductItem(new FoodProductItem("12352", "2021-03-15", foodProductService.getFoodProduct(5L)));
	}

	private static void createFoodProductValues( FoodProductService foodProductService) {
	}

}
