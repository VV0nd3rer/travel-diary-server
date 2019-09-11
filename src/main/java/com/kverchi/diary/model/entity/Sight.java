package com.kverchi.diary.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="sights")
public class Sight implements Serializable {
	@Id
	@Column(name="sight_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sightId;
	@Column(name="label")
	private String label;
	@ManyToOne
    @JoinColumn(name="country_code")
	private Country country;
	@Column(name="img_url")
	private String imgUrl;
	@Column(name="description")
	private String description;
	@Column(name="latitude")
	private float latitude;
	@Column(name="longitude")
	private float longitude;
	@JsonManagedReference
	@OneToOne(mappedBy = "sight", cascade=CascadeType.ALL)
	private SightVisitsCounter sightVisitsCounter;

	public Sight(){};
	public Sight(int sight_id) {
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

	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public SightVisitsCounter getSightVisitsCounter() {
		if (this.sightVisitsCounter == null) {
			this.sightVisitsCounter = new SightVisitsCounter(0);
		}
		return sightVisitsCounter;
	}

	public void setSightVisitsCounter(SightVisitsCounter sightVisitsCounter) {
		this.sightVisitsCounter = sightVisitsCounter;
	}
}
