package com.andrei.fleetManagement.customerTests;

import com.andrei.fleetManagement.domain.Customer;
import com.andrei.fleetManagement.service.CustomerService;
import com.andrei.fleetManagement.transfer.CreateCustomer;
import com.andrei.fleetManagement.userTests.UserServiceIntegrationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerService customerService;
    private UserServiceIntegrationTests userServiceIntegrationTests;

//    @Test
//    public void createCustomer_whenValidRequest_thenCustomerIsCreated() {
//        CreateCustomer createCustomer = new CreateCustomer();
//        createCustomer.setAddress("str. Eroilor");
//        createCustomer.setBankAccount("RO123321XXX45465");
//        createCustomer.setCif("RO123321");
//        createCustomer.setContactPerson("Test Customer");
//        createCustomer.setEmail("test@customer.com");
//        createCustomer.setName("SC Customer SRL");
//        createCustomer.setPassword("pass123");
//        createCustomer.setPhoneNumber("0123321");
//
//        Customer customer = customerService.createCustomer(createCustomer, user);
//
//        assertThat(customer, notNullValue());
//        assertThat(customer.getId(), greaterThan(0L));
//        assertThat(customer.getAddress(), is(createCustomer.getAddress()));
//        assertThat(customer.getBankAccount(), is(createCustomer.getBankAccount()));
//        assertThat(customer.getCif(), is(createCustomer.getCif()));
//        assertThat(customer.getContactPerson(), is(createCustomer.getContactPerson()));
//        assertThat(customer.getEmail(), is(createCustomer.getEmail()));
//        assertThat(customer.getPassword(), is(createCustomer.getPassword()));
//        assertThat(customer.getPhoneNumber(), is(createCustomer.getPhoneNumber()));
//    }
}
