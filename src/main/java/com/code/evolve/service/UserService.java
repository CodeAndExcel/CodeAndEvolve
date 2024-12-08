package com.code.evolve.service;

import com.code.evolve.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    User createUser(User user);
}
