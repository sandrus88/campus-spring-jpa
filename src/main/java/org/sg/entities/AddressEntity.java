package org.sg.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name = "NR")
	private String nr;
	@Column(name = "POSTAL_CODE")
	private Integer postalCode;
	@Column(name = "CITY")
	private String city;
	@Column(name = "PROVINCE_CODE")
	private String provinceCode;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
	@MapsId
    private StudentEntity studentEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
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
	
	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
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
		if (nr != null && !nr.equals(other.nr)) {
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
		result = result + ((nr == null) ? 0 : nr.hashCode());
		result = result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = result + ((city == null) ? 0 : city.hashCode());
		result = result + ((provinceCode == null) ? 0 : provinceCode.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Address  [id: " + id + ", street: " + street + ", buildingNumber: " + nr + ", postalCode: " + postalCode
				+ ", city: " + city + ", provinceCode: " + provinceCode + "studentId: " + (studentEntity != null ? studentEntity.getId() : "null") + "]";
	}
}
