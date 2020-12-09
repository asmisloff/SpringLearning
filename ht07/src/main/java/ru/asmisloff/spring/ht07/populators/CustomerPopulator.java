package ru.asmisloff.spring.ht07.populators;

import org.springframework.stereotype.Service;
import ru.asmisloff.spring.ht07.data.CustomerData;
import ru.asmisloff.spring.ht07.entities.Customer;

@Service
public class CustomerPopulator implements Populator<Customer, CustomerData> {

    @Override
    public CustomerData populate(Customer customer, CustomerData customerData) {

        if (customer == null && customerData == null) {
            return null;
        }

        customerData.setId(customer.getId());
        customerData.setCreateDate(customer.getCreateDate());
        customerData.setModifyDate(customer.getModifyDate());
        customerData.setName(customer.getName());
        customerData.setEmail(customer.getEmail());
        customerData.setPhone(customer.getPhone());
        customerData.setBirthday(customer.getBirthday());
        customerData.setAddress(customer.getAddress());
        customerData.setDescription(customer.getDescription());

        return customerData;
    }

    @Override
    public CustomerData populate(Customer customer) {
        return populate(customer, new CustomerData());
    }

}
