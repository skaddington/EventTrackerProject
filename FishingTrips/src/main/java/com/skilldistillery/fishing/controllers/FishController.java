package com.skilldistillery.fishing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Fish> allFish(){
		return fishServ.listAllFish();
	}

}
