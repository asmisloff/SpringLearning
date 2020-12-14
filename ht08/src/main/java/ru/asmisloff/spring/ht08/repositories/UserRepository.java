package ru.asmisloff.spring.ht08.repositories;

import ru.asmisloff.spring.ht08.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
