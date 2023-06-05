package com.skilldistillery.fishing.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="time_of_day")
public class TimeOfDay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String timeframe;
	
	@JsonIgnore
	@OneToMany(mappedBy="time")
	private List<CatchLog> logs;

	public TimeOfDay() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public List<CatchLog> getLogs() {
		return logs;
	}

	public void setLogs(List<CatchLog> logs) {
		this.logs = logs;
	}
	
	public void addLog(CatchLog log) {
		if (logs == null) logs = new ArrayList<>();
		if (!logs.contains(log)) {
			logs.add(log);
			if (log.getTime() != null) {
				log.getTime().removeLog(log);
			}
			log.setTime(this);
		}
	}
	
	public void removeLog(CatchLog log) {
		if (logs != null && logs.contains(log)) {
			logs.remove(log);
			log.setTime(null);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TimeOfDay [id=");
		builder.append(id);
		builder.append(", timeframe=");
		builder.append(timeframe);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeOfDay other = (TimeOfDay) obj;
		return id == other.id;
	}

}
