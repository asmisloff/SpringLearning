package ru.asmisloff.spring.ht07.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asmisloff.spring.ht07.data.CustomerData;
import ru.asmisloff.spring.ht07.entities.Customer;
import ru.asmisloff.spring.ht07.entities.views.CustomerView;
import ru.asmisloff.spring.ht07.facade.CustomerFacade;
import ru.asmisloff.spring.ht07.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers/api/v1")
public class CustomerRestController {

    CustomerService customerService;
    CustomerFacade customerFacade;

    public CustomerRestController(CustomerService customerService, CustomerFacade customerFacade) {
        this.customerService = customerService;
        this.customerFacade = customerFacade;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(CustomerView.IdName.class)
    public ResponseEntity<List<Customer>> getAllAsJson() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(CustomerView.IdNameContactInfo.class)
    public ResponseEntity<Customer> getBriefInfoById(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);

        if (customer == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/{id}/fullInfo")
    @JsonView({CustomerView.FullInfo.class})
    public ResponseEntity<Customer> getFullInfoById(@PathVariable("id") Long id) {
        return getBriefInfoById(id);
    }

    @PostMapping("/create")
    @JsonView(CustomerView.FullInfo.class)
    public Customer create(@RequestBody Customer customer) {
        customer.setId(null);
        return customerService.saveOrUpdate(customer);
    }

    @PutMapping("/update/{id}")
    @JsonView(CustomerView.FullInfo.class)
    public ResponseEntity<Customer> update(@PathVariable("id") Long id,
                                      @RequestBody Customer customer
    ) {
        if (customerService.findById(id) == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        customer.setId(id);
        customerService.saveOrUpdate(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PatchMapping("/{id}/setName")
    @JsonView(CustomerView.IdName.class)
    public ResponseEntity<Customer> setName(@PathVariable("id") Long id,
                                            @RequestParam("name") String name
    ) {
        Customer customer = customerService.findById(id);

        if (customer == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        customer.setName(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/findAllByName")
    public List<CustomerData> findAllByName(@RequestParam("name") String name) {
        return customerFacade.findAllByName(name);
    }

}
