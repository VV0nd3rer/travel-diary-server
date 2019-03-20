package com.kverchi.diary.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="countries")
public class Country {
	@Id
	@Column(name="country_code")
	private String countryCode;
	@Column(name="country_name")
	private String name;
	@Column(name="img_path")
	private String imgPath;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="country_code")
	@JsonIgnore
	private Set<CountriesSight> countriesSight;
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public Set<CountriesSight> getCountriesSight() {
		return countriesSight;
	}
	public void setCountriesSight(Set<CountriesSight> countriesSight) {
		this.countriesSight = countriesSight;
	}
	

}
