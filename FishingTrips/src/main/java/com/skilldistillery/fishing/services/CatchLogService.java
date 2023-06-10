package com.skilldistillery.fishing.services;

import java.util.List;

import com.skilldistillery.fishing.entities.CatchLog;

public interface CatchLogService {

	List<CatchLog> listAllLogs();
	CatchLog getLog(int logId);
	CatchLog create(CatchLog newLog);
	CatchLog update(int logId, CatchLog log);
	boolean delete(int logId);
	
}
