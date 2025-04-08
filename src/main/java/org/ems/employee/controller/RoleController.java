package org.ems.employee.controller;

import java.util.HashMap;
import java.util.Map;

import org.ems.employee.model.Role;
import org.ems.employee.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.persistence.EntityNotFoundException;

@Controller
public class RoleController {
	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@PostMapping("/deleteRole")
	@ResponseBody
	public ResponseEntity<?> deleteRole(@RequestParam("roleId") Long roleId) {
		Map<String, Object> response = new HashMap<>();

		try {
			// Attempt to delete the role
			roleService.deleteRole(roleId);

			// Prepare success response
			response.put("success", true);
			response.put("message", "Role deleted successfully");
			return ResponseEntity.ok(response);

		} catch (EntityNotFoundException e) {
			// Handle case where role doesn't exist
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

		} catch (IllegalStateException e) {
			// Handle case where role cannot be deleted (e.g., system role)
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);

		} catch (Exception e) {
			// Handle other unexpected errors
//			logger.error("Error deleting role:", e);
			response.put("success", false);
			response.put("message", "An unexpected error occurred while deleting the role");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/updateRole")
	@ResponseBody
	public ResponseEntity<?> updateRole(@RequestParam("roleId") Long roleId, @RequestParam("roleName") String roleName,
			@RequestParam("roleDescription") String roleDescription) {
		Map<String, Object> response = new HashMap<>();

		try {
			// Input validation
			if (roleId == null) {
				throw new IllegalArgumentException("Role ID is required");
			}
			if (roleName == null || roleName.trim().isEmpty()) {
				throw new IllegalArgumentException("Role name is required");
			}

			// Check if role exists
			Role existingRole = roleService.getRoleById(roleId);
			if (existingRole == null) {
				throw new EntityNotFoundException("Role not found with ID: " + roleId);
			}

			// Check if the new role name already exists (excluding current role)
			if (roleService.isRoleNameTaken(roleName, roleId)) {
				throw new IllegalArgumentException("Role name already exists");
			}

			// Update role
			Role updatedRole = roleService.updateRole(roleId, roleName, roleDescription);

			// Prepare success response
			response.put("success", true);
			response.put("message", "Role updated successfully");
			response.put("data", new HashMap<String, Object>() {
				{
					put("roleId", updatedRole.getRoleId());
					put("roleName", updatedRole.getRoleName());
					put("roleDescription", updatedRole.getRoleDescription());
				}
			});

			return ResponseEntity.ok(response);

		} catch (IllegalArgumentException e) {
			// Handle validation errors
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);

		} catch (EntityNotFoundException e) {
			// Handle not found errors
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

		} catch (Exception e) {
			// Handle other errors
//	        logger.error("Error updating role:", e);
			response.put("success", false);
			response.put("message", "An error occurred while updating the role");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
