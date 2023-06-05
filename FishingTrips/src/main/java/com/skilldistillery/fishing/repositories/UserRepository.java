package com.skilldistillery.fishing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.fishing.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
