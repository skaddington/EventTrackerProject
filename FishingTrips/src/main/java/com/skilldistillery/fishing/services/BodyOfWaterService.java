package com.skilldistillery.fishing.services;

import java.util.List;

import com.skilldistillery.fishing.entities.BodyOfWater;

public interface BodyOfWaterService {
	
	List<BodyOfWater> listAllBodiesOfWater();
	BodyOfWater getBodyOfWater(int waterId);
	BodyOfWater create(BodyOfWater newWater);
	BodyOfWater update(int waterId, BodyOfWater water);
	boolean delete(int waterId);

}
