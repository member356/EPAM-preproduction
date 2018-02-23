package com.epam.khrypushyna.shop.dao;

import com.epam.khrypushyna.shop.entity.User;

import java.util.List;

public interface UserDAO {

	User getUser(String login);

	void addUser(User user);

	List<User> getAllUsers();
}
