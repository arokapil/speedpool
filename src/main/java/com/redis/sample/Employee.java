package com.redis.sample;

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable{

	
	private String employeeName;
	private String employeeNumber;
	private List<String> employeePhoneNumber;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public List<String> getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}
	public void setEmployeePhoneNumber(List<String> employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}
	
	
}
