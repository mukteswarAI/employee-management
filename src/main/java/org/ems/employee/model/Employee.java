package org.ems.employee.model;


import java.time.LocalDate;

import org.ems.department.model.Department;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
	@Column(name = "empname")
    private String employeeName;
    private String address;
    private String phoneNo;
    private String email;
    private String password;
    private LocalDate dob;
    private Double salary;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    
    

	public Employee(Long employeeId, String employeeName, String address, String phoneNo, String email,String password, LocalDate dob,
			Double salary, Role role, Department department) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.salary = salary;
		this.role = role;
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee(Long employeeId, String employeeName, String address, String phoneNo, String email, LocalDate dob,
			Double salary, Role role) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.dob = dob;
		this.salary = salary;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", address=" + address
				+ ", phoneNo=" + phoneNo + ", email=" + email + ", dob=" + dob + ", salary=" + salary + "]";
	}
	
	public Employee() {
		super();
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String name) {
		this.employeeName = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Employee(String employeeName, String address, String phoneNo, String email, LocalDate dob, Double salary) {
		super();
		this.employeeName = employeeName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.dob = dob;
		this.salary = salary;
	}
		
    	
//    private Role role;
//    private Department department;
//    private List<Project> projects;
//    private List<Attendance> attendances;
//    private List<Leave> leaves;

    // Getters, Setters, Constructors
}
