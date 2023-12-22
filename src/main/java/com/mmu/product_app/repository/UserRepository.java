package com.mmu.product_app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mmu.product_app.models.User;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByEmail(String email);
    
}
