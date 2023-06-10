package com.skilldistillery.fishing.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="catch_log")
public class CatchLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate date;
	@Column(name="weight_lbs")
	private double weight;
	@Column(name="length_inch")
	private double length;
	private boolean enabled = true;
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	@Column(name="last_update")
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	
	@ManyToOne
	@JoinColumn(name="body_of_water_id")
	private BodyOfWater water;
	
	@ManyToOne
	@JoinColumn(name="fish_id")
	private Fish fish;
	
	@ManyToOne
	@JoinColumn(name="time_of_day_id")
	private TimeOfDay time;
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;
	
	public CatchLog() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BodyOfWater getWater() {
		return water;
	}

	public void setWater(BodyOfWater water) {
		this.water = water;
	}

	public Fish getFish() {
		return fish;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

	public TimeOfDay getTime() {
		return time;
	}

	public void setTime(TimeOfDay time) {
		this.time = time;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatchLog [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", length=");
		builder.append(length);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append(", water=");
		builder.append(water);
		builder.append(", fish=");
		builder.append(fish);
		builder.append(", time=");
		builder.append(time);
//		builder.append(", user=");
//		builder.append(user);
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
		CatchLog other = (CatchLog) obj;
		return id == other.id;
	}

}
