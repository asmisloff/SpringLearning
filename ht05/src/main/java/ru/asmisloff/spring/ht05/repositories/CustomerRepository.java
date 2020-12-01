package ru.asmisloff.spring.ht05.repositories;

import ru.asmisloff.spring.ht05.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
