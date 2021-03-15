package org.sg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

	@Id
	private Integer id;
	@Column(name = "STREET")
	private String street;
	@Column(name = "BUILDING_NUMBER")
	private Integer buildingNumber;
	@Column(name = "POSTAL_CODE")
	private Integer postalCode;
	@Column(name = "CITY")
	private String city;
	@Column(name = "PROVINCE_CODE")
	private String provinceCode;
	
	@OneToOne
    @JoinColumn(name = "STUDENT_ID")
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

	public Integer getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
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
