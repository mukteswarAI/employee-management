package org.ems.department.repository;

import java.util.Optional;

import org.ems.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, String> {

	Optional<Department> findByDepartmentName(String deptName);

	@Query("SELECT COUNT(d) FROM Department d")
	long countDepartments();
}
