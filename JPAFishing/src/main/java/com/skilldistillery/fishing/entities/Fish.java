package com.skilldistillery.fishing.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Fish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="common_name")
	private String commonName;
	@Column(name="scientific_name")
	private String scientificName;
	@Column(name="image_url")
	private String image;
	@Column(name="website_url")
	private String website;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="available_fish",
			joinColumns=@JoinColumn(name="fish_id"),
			inverseJoinColumns=@JoinColumn(name="body_of_water_id")	)
	private List<BodyOfWater> waters;
	
	@JsonIgnore
	@OneToMany(mappedBy="fish")
	private List<CatchLog> logs;
	
	public Fish() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
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

	public List<BodyOfWater> getWaters() {
		return waters;
	}

	public void setWaters(List<BodyOfWater> waters) {
		this.waters = waters;
	}
	
	public List<CatchLog> getLogs() {
		return logs;
	}

	public void setLogs(List<CatchLog> logs) {
		this.logs = logs;
	}

	public void addBodyOfWater(BodyOfWater water) {
		if (waters == null) waters = new ArrayList<>();
		if (!waters.contains(water)) {
			waters.add(water);
			water.addFish(this);
		}
	}
	
	public void removeBodyOfWater(BodyOfWater water) {
		if (waters != null && waters.contains(water)) {
			waters.remove(water);
			water.removeFish(this);
		}
	}
	
	public void addLog(CatchLog log) {
		if (logs == null) logs = new ArrayList<>();
		if (!logs.contains(log)) {
			logs.add(log);
			if (log.getFish() != null) {
				log.getFish().removeLog(log);
			}
			log.setFish(this);
		}
	}
	
	public void removeLog(CatchLog log) {
		if (logs != null && logs.contains(log)) {
			logs.remove(log);
			log.setFish(null);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fish [id=");
		builder.append(id);
		builder.append(", commonName=");
		builder.append(commonName);
		builder.append(", scientificName=");
		builder.append(scientificName);
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
		Fish other = (Fish) obj;
		return id == other.id;
	}
	
}
