package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.Customer;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.CustomerRepository;
import com.andrei.fleetManagement.transfer.CreateCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomer createCustomer) {
        LOGGER.info("Created customer");
        Customer customer = new Customer();
        customer.setName(createCustomer.getName());
        customer.setEmail(createCustomer.getEmail());
        customer.setPassword(createCustomer.getPassword());
        customer.setPhoneNumber(createCustomer.getPhoneNumber());
        customer.setCif(createCustomer.getCif());
        customer.setBankAccount(createCustomer.getBankAccount());
        customer.setAddress(createCustomer.getAddress());
        customer.setContactPerson(createCustomer.getContactPerson());

        return customerRepository.save(customer);
    }

//    public Customer addContractToCustomer(long customerId, Contract contract) {
//        LOGGER.info("Adding contract to customer {}", customerId);
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundExceptions("This customer was not found"));
//        List<Contract> contractList = customer.getContractList();
//        contractList.add(contract);
//
//        return customer;
//    }

    public List<Customer> getAllCustomers() {
        LOGGER.info("Retrieving all customers");
        return customerRepository.findAll();
    }

    public Customer getCustomerByName(String name) {
        LOGGER.info("Retrieving customer by name {}", name);
        return customerRepository.findByName(name);
    }

    public Customer getCustomerById(long id) {
        LOGGER.info("Retrieving customer by id {}", id);

        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("This " + id + " customer not exist"));
    }

    public void deleteCustomer(long id) {
        LOGGER.info("Deleting customer {}", id);
        customerRepository.deleteById(id);
    }

}
