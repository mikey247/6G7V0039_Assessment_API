package com.mmu.product_app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mmu.product_app.models.User;

/**
 * The UserRepository interface is responsible for providing CRUD operations for the User entity.
 * It extends the CrudRepository interface provided by Spring Data JPA.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Retrieves an Optional<User> object based on the provided email.
     *
     * @param email the email of the user to be retrieved
     * @return an Optional<User> object representing the user with the provided email, or an empty Optional if not found
     */
    Optional<User> findByEmail(String email);
    
}
