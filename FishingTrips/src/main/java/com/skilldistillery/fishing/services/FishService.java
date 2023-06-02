package com.skilldistillery.fishing.services;

import java.util.List;

import com.skilldistillery.fishing.entities.Fish;

public interface FishService {
	
	List<Fish> listAllFish();
	Fish getFish(int fishId);
	Fish create(Fish newFish);
	Fish update(int fishId, Fish fish);
	boolean delete(int fishId);

}
