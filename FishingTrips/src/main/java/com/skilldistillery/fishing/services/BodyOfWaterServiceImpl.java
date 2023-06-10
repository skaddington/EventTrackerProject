package com.skilldistillery.fishing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;

import com.skilldistillery.fishing.entities.BodyOfWater;
import com.skilldistillery.fishing.repositories.BodyOfWaterRepository;

@Service
public class BodyOfWaterServiceImpl implements BodyOfWaterService {
	
	@Autowired
	private BodyOfWaterRepository waterRepo;

	@Override
	public List<BodyOfWater> listAllBodiesOfWater() {
		return waterRepo.findAll();
	}

	@Override
	public BodyOfWater getBodyOfWater(int waterId) {
		BodyOfWater water = null;
		Optional<BodyOfWater> optWater = waterRepo.findById(waterId);
		if (optWater.isPresent()) {
			water = optWater.get();
		}
		return water;
	}

	@Override
	public BodyOfWater create(BodyOfWater newWater) {
		return waterRepo.saveAndFlush(newWater);
	}

	@Override
	public BodyOfWater update(int waterId, BodyOfWater water) {
		BodyOfWater existingWater = getBodyOfWater(waterId);
		if (existingWater != null) {
			if (water.getName() != null) {
				existingWater.setName(water.getName());
			}
			if (water.getCounty() != null) {
				existingWater.setCounty(water.getCounty());
			}
			if (water.isType() != existingWater.isType() ) {
				existingWater.setType(water.isType());
			}
			if (water.getElevationInFt() != 0) {
				existingWater.setElevationInFt(water.getElevationInFt());
			}
			if (water.getDescription() != null) {
				existingWater.setDescription(water.getDescription());
			}
			if (water.getImage() != null) {
				existingWater.setImage(water.getImage());
			}
			if (water.getWebsite() != null) {
				existingWater.setWebsite(water.getWebsite());
			}
			return waterRepo.saveAndFlush(existingWater);
		}
		return null;
	}

	@Override
	public boolean delete(int waterId) {
		boolean deleted = false;
		if (waterRepo.existsById(waterId)) {
			waterRepo.deleteById(waterId);
			deleted = true;
		}
		return deleted;
	}

}
