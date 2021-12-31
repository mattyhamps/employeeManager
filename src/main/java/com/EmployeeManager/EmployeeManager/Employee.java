package com.EmployeeManager.EmployeeManager;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "employeetable")
class Employee {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	private String firstName;
	private String lastName;
	private String designation;
	@JsonFormat(pattern="MM/dd/yyyy") //Sets the format of the date to be read in and stored from the JSON as per project requirements
	private Date dob;
	private boolean isManager;
	private int managerId;
	private String department;
	
	// Default empty constructor
	Employee(){}
		
	//Employee Constructor
	Employee(String first, String last, String des, Date d, boolean manager, int manId, String dept){
		this.firstName = first;
		this.lastName = last;
		this.designation = des;
		this.dob = d;
		this.isManager = manager;
		this.managerId = manId;
		this.department = dept;
	}
	
	
	/*
	 * Getters and Setters automatically generated
	 * 
	 * Manually changed the isManager() getters and setters to be recognized by properly by Spring 
	 */
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	
}
