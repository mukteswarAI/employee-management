package org.ems.employee.repository;

import java.util.Optional;

import org.ems.employee.model.Employee;
import org.ems.employee.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleNameAndRoleIdNot(String roleName, Long roleId);

	@Query("SELECT COUNT(r) FROM Role r")
	long countRoles();

}
