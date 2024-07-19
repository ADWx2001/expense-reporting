package com.letslearn.Modal;

public class Complaint extends User {
	private String id;
	private String telephone;
	private String typeComp;
	private String about;

	public Complaint(String name, String email, String nic) {
		super(name, email, nic);
		// TODO Auto-generated constructor stub
	}

	public Complaint(String name, String email, String nic, String id, String telephone, String typeComp,
			String about) {
		super(name, email, nic);
		this.id = id;
		this.telephone = telephone;
		this.typeComp = typeComp;
		this.about = about;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTypeComp() {
		return typeComp;
	}

	public void setTypeComp(String typeComp) {
		this.typeComp = typeComp;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
