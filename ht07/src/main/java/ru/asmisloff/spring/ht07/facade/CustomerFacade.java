package ru.asmisloff.spring.ht07.facade;

import org.springframework.stereotype.Service;
import ru.asmisloff.spring.ht07.data.CustomerData;
import ru.asmisloff.spring.ht07.populators.CustomerPopulator;
import ru.asmisloff.spring.ht07.services.CustomerService;

import java.util.List;

@Service
public class CustomerFacade {

    private CustomerPopulator customerPopulator;
    private CustomerService customerService;

    public CustomerFacade(CustomerPopulator customerPopulator, CustomerService customerService) {
        this.customerPopulator = customerPopulator;
        this.customerService = customerService;
    }

    public List<CustomerData> getAll() {
        return customerPopulator.convertAll(customerService.findAll());
    }

    public CustomerData getById(Long id) {
        return customerPopulator.populate(customerService.findById(id));
    }

    public List<CustomerData> findAllByName(String name) {
        return customerPopulator.convertAll(customerService.findAllByName(name));
    }

}
