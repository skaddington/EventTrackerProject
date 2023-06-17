package com.skilldistillery.fishing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.fishing.entities.BodyOfWater;
import com.skilldistillery.fishing.services.BodyOfWaterService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class BodyOfWaterController {

	@Autowired
	private BodyOfWaterService waterServ;

	@GetMapping("waters")
	public List<BodyOfWater> listAllWaters() {
		return waterServ.listAllBodiesOfWater();
	}

	@GetMapping("waters/{waterId}")
	public BodyOfWater findWaterById(HttpServletResponse res, @PathVariable int waterId) {
		BodyOfWater water = waterServ.getBodyOfWater(waterId);
		if (water == null) {
			res.setStatus(404);
		}
		return water;
	}

	@PostMapping("waters")
	public BodyOfWater createWater(HttpServletResponse res, HttpServletRequest req, @RequestBody BodyOfWater newWater) {
		try {
			newWater = waterServ.create(newWater);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newWater.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			newWater = null;
		}

		return newWater;
	}
	
	@PutMapping("waters/{waterId}")
	public BodyOfWater updateWater(HttpServletResponse res, @PathVariable int waterId, @RequestBody BodyOfWater water) {
		try {
			water = waterServ.update(waterId, water);
			if (water == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			water = null;
		}
		return water;
	}
	
	@DeleteMapping("waters/{waterId}")
	public void deleteWater(HttpServletResponse res, @PathVariable int waterId) {
		if (waterServ.delete(waterId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}

}
