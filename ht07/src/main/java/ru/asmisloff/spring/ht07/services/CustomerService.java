package ru.asmisloff.spring.ht07.services;

import org.springframework.stereotype.Service;
import ru.asmisloff.spring.ht07.entities.Customer;
import ru.asmisloff.spring.ht07.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAllByName(String name) {
        return customerRepository.findAllByNameIgnoreCase(name);
    }
}
