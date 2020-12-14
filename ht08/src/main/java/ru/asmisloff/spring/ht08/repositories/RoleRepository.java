package ru.asmisloff.spring.ht08.repositories;

import ru.asmisloff.spring.ht08.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
