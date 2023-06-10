package com.skilldistillery.fishing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.fishing.entities.BodyOfWater;
import com.skilldistillery.fishing.entities.CatchLog;
import com.skilldistillery.fishing.services.CatchLogService;

@RestController
@RequestMapping("api")
public class CatchLogController {

	@Autowired
	private CatchLogService logServ;

	@GetMapping("logs")
	public List<CatchLog> listAllLogs() {
		return logServ.listAllLogs();
	}

	@GetMapping("logs/{logId}")
	public CatchLog findLogById(HttpServletResponse res, @PathVariable int logId) {
		CatchLog log = logServ.getLog(logId);
		if (log == null) {
			res.setStatus(404);
		}
		return log;
	}

	@PostMapping("logs")
	public CatchLog createLog(HttpServletResponse res, HttpServletRequest req, @RequestBody CatchLog newLog) {
		try {
			newLog = logServ.create(newLog);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newLog.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			newLog = null;
		}
		return newLog;
	}
	
	@PutMapping("logs/{logId}")
	public CatchLog updateLog(HttpServletResponse res, @PathVariable int logId, @RequestBody CatchLog log) {
		try {
			log = logServ.update(logId, log);
			if (log == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			log = null;
		}
		return log;
	}
	
	@DeleteMapping("logs/{logId}")
	public void deleteLog(HttpServletResponse res, @PathVariable int logId) {
		if (logServ.delete(logId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}

}
