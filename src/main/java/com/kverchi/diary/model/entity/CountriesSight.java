package com.kverchi.diary.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="countries_sights")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CountriesSight implements Serializable {
	@Id
	@Column(name="sight_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sightId;
	@Column(name="sight_label")
	private String label;
	
	@ManyToOne
    @JoinColumn(name="country_code")
	private Country country;
	
	/*@Column(name="country_code")
	private String country_code;*/
	
	@Column(name="img_url")
	private String imgUrl;
	@Column(name="description")
	private String description;
	@Column(name="map_coord_x")
	private float mapCoordX;
	@Column(name="map_coord_y")
	private float mapCoordY;
	
	/*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="sight_id")
	Set<Post> sight_posts;*/
	public CountriesSight(){};
	public CountriesSight(int sight_id) {
		this.sightId = sight_id;
	}
	public int getSightId() {
		return sightId;
	}
	public void setSightId(int sightId) {
		this.sightId = sightId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public float getMapCoordX() {
		return mapCoordX;
	}
	public void setMapCoordX(float mapCoordX) {
		this.mapCoordX = mapCoordX;
	}
	public float getMapCoordY() {
		return mapCoordY;
	}
	public void setMapCoordY(float mapCoordY) {
		this.mapCoordY = mapCoordY;
	}
   
}
