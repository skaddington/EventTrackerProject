package com.skilldistillery.fishing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.fishing.entities.User;
import com.skilldistillery.fishing.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"8086", "http://localhost/"})
public class UserController {

	@Autowired
	private UserService userServ;

	@GetMapping("users")
	public List<User> listAllUsers() {
		return userServ.getAllUsers();
	}

	@GetMapping("users/{userId}")
	public User findUserById(HttpServletResponse res, @PathVariable int userId) {
		User user = userServ.getUser(userId);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	@PostMapping("users")
	public User createUser(HttpServletResponse res, HttpServletRequest req, @RequestBody User newUser) {
		try {
			newUser = userServ.create(newUser);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newUser.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			newUser = null;
		}
		return newUser;
	}

	@PutMapping("users/{userId}")
	public User updateUser(HttpServletResponse res, @PathVariable int userId, @RequestBody User user) {
		try {
			user = userServ.update(userId, user);
			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}

	@DeleteMapping("users/{userId}")
	public void deleteUser(HttpServletResponse res, @PathVariable int userId) {
		if (userServ.delete(userId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}
}
