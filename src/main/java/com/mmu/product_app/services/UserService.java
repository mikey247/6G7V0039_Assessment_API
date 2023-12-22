package com.mmu.product_app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mmu.product_app.EntityNotFoundException;
import com.mmu.product_app.models.User;
import com.mmu.product_app.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    public User getUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return unwrapUser(user, 404L);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
    
}
