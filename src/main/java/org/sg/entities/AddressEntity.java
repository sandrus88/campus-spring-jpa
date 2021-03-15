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
	private String buildingNumber;
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

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
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
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof AddressEntity)) {
			return false;
		}
		AddressEntity other = (AddressEntity) o;
		if (id != other.id) {
			return false;
		}
		if (street != null && !street.equals(other.street)) {
			return false;
		}
		if (buildingNumber != null && !buildingNumber.equals(other.buildingNumber)) {
			return false;
		}
		if (postalCode != null && !postalCode.equals(other.postalCode)) {
			return false;
		}
		if (city != null && !city.equals(other.city)) {
			return false;
		}
		if (provinceCode != null && !provinceCode.equals(other.provinceCode)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = result + ((street == null) ? 0 : street.hashCode());
		result = result + ((buildingNumber == null) ? 0 : buildingNumber.hashCode());
		result = result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = result + ((city == null) ? 0 : city.hashCode());
		result = result + ((provinceCode == null) ? 0 : provinceCode.hashCode());
		return result;
	}
}
