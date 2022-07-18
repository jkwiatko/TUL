package com.j4c08.uwatch.data.service;

import com.j4c08.uwatch.data.models.User;
import com.j4c08.uwatch.data.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final
    UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<User> getAllUsers() {
        return new ArrayList<>(userRepo.findAll());
    }

    public void addUser(User user) {
        userRepo.save(user);
    }

    public User getUser(Integer id) {
        return userRepo.getOne(id);
    }


    public void updateUser(User user) {
        userRepo.save(user);
    }

    public void  deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
}
