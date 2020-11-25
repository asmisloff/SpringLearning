package ru.asmisloff.springData.services;

import ru.asmisloff.springData.entity.User;

import java.util.List;

public interface UserService {

    User get(Long id);
    List<User> getAll();
    void save(User user);
    void remove(User user);

}
