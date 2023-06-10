package com.skilldistillery.fishing.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "body_of_water")
public class BodyOfWater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String county;
	private boolean type;
	@Column(name = "elevation_ft")
	private int elevationInFt;
	private String description;
	@Column(name = "image_url")
	private String image;
	@Column(name = "website_url")
	private String website;

	@JsonIgnore
	@ManyToMany(mappedBy = "waters")
	private List<Fish> fishies;
	
	@JsonIgnore
	@OneToMany(mappedBy="water")
	private List<CatchLog> logs;

	public BodyOfWater() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public int getElevationInFt() {
		return elevationInFt;
	}

	public void setElevationInFt(int elevationInFt) {
		this.elevationInFt = elevationInFt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Fish> getFishies() {
		return fishies;
	}

	public void setFishies(List<Fish> fishies) {
		this.fishies = fishies;
	}

	public List<CatchLog> getLogs() {
		return logs;
	}

	public void setLogs(List<CatchLog> logs) {
		this.logs = logs;
	}

	public void addFish(Fish fish) {
		if (fishies == null) fishies = new ArrayList<>();
		if (!fishies.contains(fish)) {
			fishies.add(fish);
			fish.addBodyOfWater(this);
		}
	}

	public void removeFish(Fish fish) {
		if (fishies != null && fishies.contains(fish)) {
			fishies.remove(fish);
			fish.removeBodyOfWater(this);
		}
	}
	
	public void addLog(CatchLog log) {
		if (logs == null) logs = new ArrayList<>();
		if (!logs.contains(log)) {
			logs.add(log);
			if (log.getWater() != null) {
				log.getWater().removeLog(log);
			}
			log.setWater(this);
		}
	}

	public void removeLog(CatchLog log) {
		if (logs != null && logs.contains(log)) {
			logs.remove(log);
			log.setWater(null);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BodyOfWater [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", county=");
		builder.append(county);
		builder.append(", type=");
		builder.append(type);
		builder.append(", elevationInFt=");
		builder.append(elevationInFt);
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
		BodyOfWater other = (BodyOfWater) obj;
		return id == other.id;
	}

}
