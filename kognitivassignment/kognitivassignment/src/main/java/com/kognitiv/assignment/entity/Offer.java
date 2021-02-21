package com.kognitiv.assignment.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Offer {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Timestamp validFrom;
	private Timestamp validTill;
	private String location;
	private String images;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}
	public Timestamp getValidTill() {
		return validTill;
	}
	public void setValidTill(Timestamp validTill) {
		this.validTill = validTill;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
}
