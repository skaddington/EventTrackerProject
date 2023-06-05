package com.skilldistillery.fishing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.fishing.entities.Fish;
import com.skilldistillery.fishing.repositories.FishRepository;

@Service
public class FishServiceImpl implements FishService {
	
	@Autowired
	private FishRepository fishRepo;

	@Override
	public List<Fish> listAllFish() {
		return fishRepo.findAll();
	}

	@Override
	public Fish getFish(int fishId) {
		Fish fish = null;
		Optional<Fish> optFish = fishRepo.findById(fishId);
		if (optFish.isPresent()) {
			fish = optFish.get();
		}
		return fish;
	}

	@Override
	public Fish create(Fish newFish) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fish update(int fishId, Fish fish) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int fishId) {
		// TODO Auto-generated method stub
		return false;
	}

}
