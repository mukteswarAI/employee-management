package org.ems.department.model;

import java.util.List;

import org.ems.employee.model.Employee;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
	@Id
	@GeneratedValue(generator = "custom-generator")
	@GenericGenerator(name = "custom-generator", strategy = "org.ems.utils.CustomIdGenerator")
	private String departmentId;
	private String departmentName;
	private String description;
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	public Department(String departmentId, String departmentName, String description) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.description = description;
	}

	public Department(String departmentId, String departmentName, String description, List<Employee> employees) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.description = description;
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Department() {
		super();
	}

	@Override
	public String toString() {
		return "Department{" + "departmentId='" + departmentId + '\'' + ", departmentName='" + departmentName + '\''
				+ ", description='" + description + '\'' + '}';
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
