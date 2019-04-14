package ch.practice.service.impl;

import ch.practice.pojo.User;
import ch.practice.repository.UserRepository;
import ch.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {

        User u = userRepository.save(user);
        return u;
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public int delete(Long id) {
        userRepository.deleteById(id);
        return 0;
    }

    @Override
    public User findUserById(Long id) {

        Optional<User> user = userRepository.findById(id);
        if(null != user){
            return user.get();
        }
        return null;
    }

    @Override
    public List<User> findUsers() {

        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User findUserByName(String name) {


       return userRepository.findUserByName(name);

    }
}
