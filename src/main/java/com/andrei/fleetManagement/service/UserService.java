package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.User;
import com.andrei.fleetManagement.persistance.UserRepository;
import com.andrei.fleetManagement.transfer.CreateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUser createUser) {
        LOGGER.info("Creating new user");

        User user = new User();
        user.setName(createUser.getName());
        user.setEmail(createUser.getEmail());
        user.setPassword(createUser.getPassword());
        user.setPhoneNumber(createUser.getPhoneNumber());

        return userRepository.save(user);
    }

    public User getUserById(long id) {
        LOGGER.info("Retrieving user by id {}", id);
        return userRepository.getOne(id);
    }

    public User getUserByName(String name) {
        LOGGER.info("Retrieving user by name {}", name);
        return userRepository.findByName(name);
    }

    public void deleteUser(long id) {
        LOGGER.info("Deleting user by id {}", id);
        userRepository.deleteById(id);
    }


}
