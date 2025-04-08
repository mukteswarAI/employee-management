package org.ems.employee.repository;

import java.util.List;

import org.ems.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByDepartmentDepartmentId(String departmentId);

	List<Employee> findByRoleRoleId(Long roleId);

	Employee findByEmailAndPassword(String email, String password);

	Employee findByEmail(String email);
	
	@Query("SELECT COUNT(e) FROM Employee e")
	long countEmployees();
	}
