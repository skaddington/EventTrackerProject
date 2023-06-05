package com.skilldistillery.fishing.services;

import java.util.List;

import com.skilldistillery.fishing.entities.User;

public interface UserService {

	List<User> getAllUsers();
	User getUser(int userId);
	User create(User newUser);
	User update(int userId, User user);
	boolean delete(int userId);
}
