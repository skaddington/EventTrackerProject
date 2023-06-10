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
		return fishRepo.saveAndFlush(newFish);
	}

	@Override
	public Fish update(int fishId, Fish fish) {
		Fish existingFish = getFish(fishId);
		if ( existingFish != null) {
			if ( fish.getCommonName() != null) {
				existingFish.setCommonName(fish.getCommonName());
			}
			if ( fish.getScientificName() != null) {
				existingFish.setScientificName(fish.getScientificName());
			}
			if ( fish.getImage() != null) {
				existingFish.setImage(fish.getImage());
			}
			if ( fish.getWebsite() != null) {
				existingFish.setWebsite(fish.getWebsite());
			}
			return fishRepo.saveAndFlush(existingFish);
		}
		return null;
	}

	@Override
	public boolean delete(int fishId) {
		boolean deleted = false;
		if (fishRepo.existsById(fishId)) {
			fishRepo.deleteById(fishId);
			deleted = true;
		}
		return deleted;
	}
}
