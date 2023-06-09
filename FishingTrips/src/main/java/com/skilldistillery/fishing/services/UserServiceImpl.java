package com.skilldistillery.fishing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.fishing.entities.User;
import com.skilldistillery.fishing.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUser(int userId) {
		User user = null;
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			user = optUser.get();
		}
		return user;
	}

	@Override
	public User create(User newUser) {
		return userRepo.saveAndFlush(newUser);
	}

	@Override
	public User update(int userId, User user) {
		User existingUser = getUser(userId);
		if (existingUser != null) {
			if (user.getUsername() != existingUser.getUsername()) {
				existingUser.setUsername(user.getUsername());
			}
			if (user.getPassword() != null) {
				existingUser.setPassword(user.getPassword());
			}
			if (user.getFirstName() != null) {
				existingUser.setFirstName(user.getFirstName());
			}
			if (user.getLastName() != null) {
				existingUser.setLastName(user.getLastName());
			}
			if (user.getImage() != null) {
				existingUser.setImage(user.getImage());
			}
			return userRepo.saveAndFlush(existingUser);
		}
		return null;
	}

	@Override
	public boolean delete(int userId) {
		boolean deleted = false;
		if (userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			deleted = true;
		}
		return deleted;
	}

}
