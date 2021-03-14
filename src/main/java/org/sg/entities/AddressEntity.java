package org.sg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressEntity {

	@Id
	private Integer id;
	@Column(name = "STREET")
	private String street;
	@Column(name = "BUILDING_NUMBER")
	private int buildingNumber;
	@Column(name = "POSTAL_CODE")
	private int postalCode;
	@Column(name = "CITY")
	private String city;
	@Column(name = "PROVINCE_CODE")
	private String provinceCode;
	
	@OneToOne
    @JoinColumn(name = "ID")
    @MapsId
    private StudentEntity studentEntity;

	public Integer getId() {
		id = studentEntity.getId();
		return id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}
}
