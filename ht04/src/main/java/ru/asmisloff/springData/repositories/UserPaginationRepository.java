package ru.asmisloff.springData.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.asmisloff.springData.entity.User;

@Repository
public interface UserPaginationRepository extends PagingAndSortingRepository<User, Long> {


}
