package com.andrei.fleetManagement.userTests;

import com.andrei.fleetManagement.domain.User;
import com.andrei.fleetManagement.service.UserService;
import com.andrei.fleetManagement.transfer.CreateUser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    public void createUser_whenValidRequest_thenUserIsCreated() {
        CreateUser createUser = new CreateUser();
        createUser.setUsername("test");
        createUser.setEmail("test@test.com");
        createUser.setPassword("test");
        createUser.setPhoneNumber("000000");
        createUser.setPermission("");
        createUser.setRole("USER");

        User user = userService.createUser(createUser);

        assertThat(user, notNullValue());
        assertThat(user.getId(), greaterThan(0L));
        assertThat(user.getUsername(), is(createUser.getEmail()));
        assertThat(user.getEmail(), is(createUser.getEmail()));
        assertThat(user.getPassword(), Matchers.not(createUser.getPassword()));
        assertThat(user.getPhoneNumber(), is(createUser.getPhoneNumber()));
    }

    @Test
    public void createUser_whenMissingName_thenThrowException() {
        CreateUser createUser = new CreateUser();
        createUser.setEmail("test@test.com");
        createUser.setPassword("test");
        createUser.setPhoneNumber("000000");

        try {
            userService.createUser(createUser);
        } catch (Exception e) {
            assertThat(e, notNullValue());
            assertThat("Unexpected exception type.", e instanceof TransactionSystemException);
        }
    }
}
