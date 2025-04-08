package org.ems.employee.service;

import java.util.List;

import org.ems.employee.model.Employee;
import org.ems.employee.model.Role;
import org.ems.employee.repository.EmployeeRepository;
import org.ems.employee.repository.RoleRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;
	private final EmployeeRepository employeeRepository;

	public RoleServiceImpl(RoleRepository roleRepository, EmployeeRepository employeeRepository) {
		this.roleRepository = roleRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void createRole(String roleName, String roleDesc) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setRoleName(roleName);
		role.setRoleDescription(roleDesc);
		roleRepository.save(role);

	}

	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public void assignRoleToEmployee(Long employeeId, Long roleId) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepository.findById(employeeId).get();
		Role role = roleRepository.findById(roleId).get();
		emp.setRole(role);
		employeeRepository.save(emp);
	}

	@Override
	public Role getRoleById(Long roleId) {
		// TODO Auto-generated method stub
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + roleId));
	}

	@Override
	public boolean isRoleNameTaken(String roleName, Long roleId) {
		// TODO Auto-generated method stub
		return roleRepository.findByRoleNameAndRoleIdNot(roleName, roleId).isPresent();
	}

	@Override
	public Role updateRole(Long roleId, String roleName, String roleDescription) {
		// TODO Auto-generated method stub
		Role role = getRoleById(roleId);

		// Update fields
		role.setRoleName(roleName.trim());
		role.setRoleDescription(roleDescription != null ? roleDescription.trim() : null);

		// Save and return updated role
		return roleRepository.save(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		// TODO Auto-generated method stub
		try {
			// Check if role exists
			Role role = roleRepository.findById(roleId)
					.orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + roleId));

			// Find all employees with this role
			List<Employee> employeesWithRole = employeeRepository.findByRoleRoleId(roleId);

			if (!employeesWithRole.isEmpty()) {
//	                logger.info("Found {} employees with role ID: {}. Unlinking role...", 
//	                    employeesWithRole.size(), roleId);

				// Unlink role from employees
				for (Employee employee : employeesWithRole) {
					employee.setRole(null);
					employeeRepository.save(employee);
//	                    logger.debug("Unlinked role from employee ID: {}", employee.getEmployeeId());
				}
			}

			// Delete the role
			roleRepository.delete(role);
//	            logger.info("Successfully deleted role with ID: {}", roleId);

		} catch (Exception e) {
//	            logger.error("Error deleting role with ID: {}", roleId, e);
			throw new RuntimeException("Failed to delete role: " + e.getMessage());
		}

	}

	@Override
	public long getRolesCount() {
		// TODO Auto-generated method stub
		return roleRepository.countRoles();
	}

}
