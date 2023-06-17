package com.skilldistillery.fishing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.fishing.entities.Fish;
import com.skilldistillery.fishing.services.FishService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class FishController {

	@Autowired
	private FishService fishServ;

	@GetMapping("fish")
	public List<Fish> listAllFish() {
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

	@PostMapping("fish")
	public Fish createFish(HttpServletResponse res, HttpServletRequest req, @RequestBody Fish newFish) {
		try {
			newFish = fishServ.create(newFish);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newFish.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			newFish = null;
		}

		return newFish;
	}
	
	@PutMapping("fish/{fishId}")
	public Fish updateFish(HttpServletResponse res, @PathVariable int fishId, @RequestBody Fish fish) {
		try {
			fish = fishServ.update(fishId, fish);
			if (fish == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			fish = null;
		}
		return fish;
	}
	
	@DeleteMapping("fish/{fishId}")
	public void deleteFish(HttpServletResponse res, @PathVariable int fishId) {
		if (fishServ.delete(fishId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}

}
