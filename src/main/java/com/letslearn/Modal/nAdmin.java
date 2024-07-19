package com.letslearn.Modal;

public class nAdmin extends User {
	private String id;
	private String adminName;
	private String department;
	private String telephone;

	public nAdmin (String name, String email, String nic, String id, String adminName, String department, String telephone) {
		super(name, email, nic);
		this.id = id;
		this.adminName = adminName;
		this.department = department;
		this.telephone = telephone;
	}

	public nAdmin(String name, String email, String nic) {
		super(name, email, nic);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
