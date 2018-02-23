package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.Validator;
import com.epam.khrypushyna.shop.dao.UserDAO;
import com.epam.khrypushyna.shop.db.TransactionManager;
import com.epam.khrypushyna.shop.entity.User;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private TransactionManager transactionManager;
    private Map<String, Object> messageMap;

    public UserServiceImpl(UserDAO userDAO, TransactionManager transactionManager, Validator validator) {
        this.userDAO = userDAO;
        this.transactionManager = transactionManager;
        this.messageMap = validator.getMessagesMap();
    }

    @Override
    public boolean checkLogin(User newUser) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getLogin().equals(newUser.getLogin())) {
                messageMap.put("loginerror", "User with this login is already exist");
                return false;
            }
        }
        return true;
    }

    @Override
    public User login(String login, String password) {
        User user = getUserByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                messageMap.put("passworderror", "Incorrect password, try again");
            }
        } else {
            messageMap.put("loginerror", "User with this login doesn't exist");
        }
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return (User) transactionManager.doTransaction(() -> userDAO.getUser(login));
    }

    @Override
    public void addUser(User user) {
        transactionManager.doTransaction(() -> {
            userDAO.addUser(user);
            return null;
        });
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) transactionManager.doTransaction(() -> userDAO.getAllUsers());
    }

}
