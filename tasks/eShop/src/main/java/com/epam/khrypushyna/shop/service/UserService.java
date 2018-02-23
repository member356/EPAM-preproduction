package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    User getUserByLogin(String login);

    boolean checkLogin(User newUser);

    User login(String login, String password);
}
