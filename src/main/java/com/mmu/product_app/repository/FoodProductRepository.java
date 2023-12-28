/**
 * The FoodProductRepository interface is responsible for providing CRUD operations
 * for the FoodProduct entity. It extends the CrudRepository interface provided by
 * Spring Data JPA, which allows for easy implementation of basic database operations.
 * 
 * This interface is annotated with the @Repository annotation, indicating that it is
 * a Spring Data repository component. It is automatically scanned and instantiated
 * by the Spring framework during application startup.
 * 
 * The FoodProductRepository interface extends the CrudRepository interface, which
 * provides generic CRUD (Create, Read, Update, Delete) operations for the FoodProduct
 * entity. It also specifies the type of the entity (FoodProduct) and the type of the
 * entity's primary key (Long).
 */
package com.mmu.product_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.mmu.product_app.models.FoodProduct;



public interface FoodProductRepository extends CrudRepository<FoodProduct,Long> {

    
} 
