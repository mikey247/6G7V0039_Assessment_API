package com.mmu.product_app.repository;

import com.mmu.product_app.models.FoodProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodProductItem, Long> {
}
