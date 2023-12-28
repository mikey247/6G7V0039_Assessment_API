/**
 * The FoodItemRepository interface is responsible for providing CRUD operations
 * for the FoodProductItem entity in the database.
 * <p>
 * This interface extends the JpaRepository interface, which provides generic
 * CRUD operations and querying capabilities for the FoodProductItem entity.
 * It also specifies the type of the entity (FoodProductItem) and the type of
 * the primary key (Long).
 */
package com.mmu.product_app.repository;

import com.mmu.product_app.models.FoodProductItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodItemRepository extends JpaRepository<FoodProductItem, Long> {
}
