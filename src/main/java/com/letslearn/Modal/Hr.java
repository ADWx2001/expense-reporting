package com.letslearn.Modal;

public class Hr extends User {
	private String city;
	private String grade;
	private String tel;

	public Hr(String name, String email, String nic, String city, String grade, String tel) {
		super(name, email, nic);
		this.city = city;
		this.grade = grade;
		this.tel = tel;
	}

	public Hr(String name, String email, String nic) {
		super(name, email, nic);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
