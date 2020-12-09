package ru.asmisloff.spring.ht07.repositories;

import ru.asmisloff.spring.ht07.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByNameIgnoreCase(String name);
}
