package ru.asmisloff.springData.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asmisloff.springData.entity.User;

@Repository
public interface UserCrudRepository extends JpaRepository<User, Long> {

}
