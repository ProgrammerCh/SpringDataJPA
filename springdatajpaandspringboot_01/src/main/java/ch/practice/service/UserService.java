package ch.practice.service;

import ch.practice.pojo.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User update(User user);
    int delete(Long id);
    User findUserById(Long id);
    List<User> findUsers();
    User findUserByName(String name);
}