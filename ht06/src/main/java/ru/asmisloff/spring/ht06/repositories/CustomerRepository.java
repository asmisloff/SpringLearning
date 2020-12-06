package ru.asmisloff.spring.ht06.repositories;

import ru.asmisloff.spring.ht06.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
