package com.skilldistillery.fishing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.fishing.entities.Fish;
import com.skilldistillery.fishing.services.FishService;

@RestController
@RequestMapping("api")
public class FishController {
	
	@Autowired
	private FishService fishServ;
	
	@GetMapping("fish")
	public List<Fish> listAllFish(){
		return fishServ.listAllFish();
	}
	
	@GetMapping("fish/{fishId}")
	public Fish findFishById(HttpServletResponse res, @PathVariable int fishId) {
		Fish fish = fishServ.getFish(fishId);
		if (fish == null) {
			res.setStatus(404);
		}
		return fish;
	}

}
