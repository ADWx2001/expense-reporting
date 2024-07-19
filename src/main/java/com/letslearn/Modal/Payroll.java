package com.letslearn.Modal;

public class Payroll extends User{
	
	private String paymentFor;
	private String bonus;
	private String salary;
	
		
	
	public Payroll(String name, String email, String nic) {
		super(name, email, nic);
	}
	
	public Payroll(String name, String email, String nic, String paymentFor, String bonus, String salary) {
		super(name, email, nic);
		this.paymentFor = paymentFor;
		this.bonus = bonus;
		this.salary = salary;
	}
	public String getPaymentFor() {
		return paymentFor;
	}
	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

	

}
