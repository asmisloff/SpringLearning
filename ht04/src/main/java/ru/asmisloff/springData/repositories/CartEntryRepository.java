package ru.asmisloff.springData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asmisloff.springData.entity.CartEntry;

@Repository
public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {

}

