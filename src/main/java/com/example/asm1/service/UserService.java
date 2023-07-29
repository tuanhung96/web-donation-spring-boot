package com.example.asm1.service;

import com.example.asm1.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();

    void saveUser(User user);

    boolean userExists(String email);

    User findById(int theId);

    void deleteUser(int userId);
}
