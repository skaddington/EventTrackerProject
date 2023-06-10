package com.skilldistillery.fishing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.fishing.entities.TimeOfDay;

public interface TimeOfDayRepository extends JpaRepository<TimeOfDay, Integer> {

}
