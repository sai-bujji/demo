package com.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Integer employeeId;
	
	@NotBlank
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Email
	@NotBlank
	@Column(name = "email_address", nullable = false)
	private String email;
	
	@OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
	private List<EmployeePhoneNumbers> employeePhoneNumbers; 
	
	@NotBlank
	@Column(name = "date_of_joining", nullable = false)
	private String doj;
	
	@NotNull
	@Column(name = "salary", nullable = false)
	private double salary;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<EmployeePhoneNumbers> getEmployeePhoneNumbers() {
		return employeePhoneNumbers;
	}

	public void setEmployeePhoneNumbers(List<EmployeePhoneNumbers> employeePhoneNumbers) {
		this.employeePhoneNumbers = employeePhoneNumbers;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
