package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.User;
import com.andrei.fleetManagement.persistance.UserRepository;
import com.andrei.fleetManagement.transfer.CreateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUser createUser) {
        LOGGER.info("Creating new user");

        User user = new User(createUser.getUsername(),
                passwordEncoder.encode(createUser.getPassword()),
                createUser.getRole(),
                createUser.getPermission());

        user.setPhoneNumber(createUser.getPhoneNumber());
        user.setActive(1);
        user.setUsername(createUser.getEmail());
        user.setEmail(createUser.getEmail());

        return userRepository.save(user);
    }

    public User getUserById(long id) {
        LOGGER.info("Retrieving user by id {}", id);
        return userRepository.getOne(id);
    }

    public User getUserByName(String name) {
        LOGGER.info("Retrieving user by name {}", name);
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("This user " + name + " does not exist"));
    }

    public void deleteUser(long id) {
        LOGGER.info("Deleting user by id {}", id);
        userRepository.deleteById(id);
    }



}
