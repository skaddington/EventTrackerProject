package com.skilldistillery.fishing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.fishing.entities.CatchLog;
import com.skilldistillery.fishing.repositories.CatchLogRepository;

@Service
public class CatchLogServiceImpl implements CatchLogService {
	@Autowired
	private CatchLogRepository logRepo;

	@Override
	public List<CatchLog> listAllLogs() {
		return logRepo.findAll();
	}

	@Override
	public CatchLog getLog(int logId) {
		CatchLog log = null;
		Optional<CatchLog> optLog = logRepo.findById(logId);
		if (optLog.isPresent()) {
			log = optLog.get();
		}
		return log;
	}

	@Override
	public CatchLog create(CatchLog newLog) {
		return logRepo.saveAndFlush(newLog);
	}

	@Override
	public CatchLog update(int logId, CatchLog log) {
		CatchLog existingLog = getLog(logId);
		if (existingLog != null) {
			if (log.getDate() != null) {
				existingLog.setDate(log.getDate());
			}
			if (log.getWeight() != 0) {
				existingLog.setWeight(log.getWeight());
			}
			if (log.getLength() != 0) {
				existingLog.setLength(log.getLength());
			}
			if (log.isEnabled() != existingLog.isEnabled()) {
				existingLog.setEnabled(log.isEnabled());
			}
			if (log.getFish() != null) {
				existingLog.setFish(log.getFish());
			}
			if (log.getWater() != null) {
				existingLog.setWater(log.getWater());
			}
			if (log.getTime() != null) {
				existingLog.setTime(log.getTime());
			}
			return logRepo.saveAndFlush(existingLog);
		}
		return null;
	}

	@Override
	public boolean delete(int logId) {
		boolean deleted = false;
		if (logRepo.existsById(logId)) {
			logRepo.deleteById(logId);
			deleted = true;
		}
		return deleted;
	}

}
