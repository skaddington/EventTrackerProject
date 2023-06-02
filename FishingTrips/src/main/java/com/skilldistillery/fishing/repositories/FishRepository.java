package com.skilldistillery.fishing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.fishing.entities.Fish;

public interface FishRepository extends JpaRepository<Fish, Integer> {

}
