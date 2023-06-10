package com.skilldistillery.fishing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.fishing.entities.CatchLog;

public interface CatchLogRepository extends JpaRepository<CatchLog, Integer> {

}
