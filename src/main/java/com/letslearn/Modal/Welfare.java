package com.letslearn.Modal;

public class Welfare extends User {
	private String id;
	private String company;
	private String connection;
	private String telephone;

	public Welfare(String name, String email, String nic, String id, String company, String connection, String telephone) {
		super(name, email, nic);
		this.id = id;
		this.company = company;
		this.connection = connection;
		this.telephone = telephone;
	}

	public Welfare(String name, String email, String nic) {
		super(name, email, nic);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
