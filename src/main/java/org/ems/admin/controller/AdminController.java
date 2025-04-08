package org.ems.admin.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ems.department.model.Department;
import org.ems.department.service.DepartmentService;
import org.ems.employee.model.Employee;
import org.ems.employee.model.Role;
import org.ems.employee.service.EmployeeService;
import org.ems.employee.service.RoleService;
import org.ems.utils.PdfReportGenerator;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {
	private final EmployeeService employeeService;
	private final RoleService roleService;
	private final DepartmentService deptService;
	private final PdfReportGenerator pdfReportGenerator;

	public AdminController(EmployeeService employeeService, RoleService roleService, DepartmentService deptService,
			PdfReportGenerator pdfReportGenerator) {
		this.employeeService = employeeService;
		this.roleService = roleService;
		this.deptService = deptService;
		this.pdfReportGenerator = pdfReportGenerator;
	}

	@GetMapping("/admin")
	public String index(Model model) {
		long empCount = employeeService.getTotalEmployee();
		long deptCount = deptService.getTotalDeptCount();
		long roleCount = roleService.getRolesCount();
		System.out.println(empCount);
		model.addAttribute("empcount", empCount);
		model.addAttribute("deptcount", deptCount);
		model.addAttribute("rolecount", roleCount);
		return "index";
	}

	@GetMapping("/generate-report")
	public ResponseEntity<InputStreamResource> generateReport() throws IOException {
		ByteArrayInputStream reportStream = pdfReportGenerator.generateCompanyReport();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=company_report.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(reportStream));
	}

	@GetMapping("/emplist")
	public String employeeList(Model model) {
		List<Employee> employees = employeeService.getAllEmployee();
		model.addAttribute("emplist", employees);
		System.out.println(employees.toString());
		return "employee-table";
	}

	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}

	@GetMapping("/addemp")
	public String addEmployee() {
		return "AddEmp";
	}

	@RequestMapping(value = "/addEmployee", method = { RequestMethod.POST })
	public String submitEmpForm(@RequestParam("employeeName") String name, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("phoneNo") String phoneNo,
			@RequestParam("dob") String dobString, @RequestParam("salary") Double salary,
			@RequestParam("password") String password) {
//		if(result.hasErrors()) {
//			return "redirect:/emplist";
//    	@ModelAttribute("empForm") Employee emp,
//    	, BindingResult result
//		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(dobString, formatter);
//		System.out.println(name + " " + email + " " + phoneNo + " " + dob + " " + salary);
//	   System.out.println(emp.toString());
		Employee newEmp = new Employee();
		newEmp.setEmployeeName(name);
		newEmp.setEmail(email);
		newEmp.setPassword(password);
		newEmp.setAddress(address);
		newEmp.setDob(dob);
		newEmp.setPhoneNo(phoneNo);
		newEmp.setSalary(salary);
		employeeService.addEmployee(newEmp);
		// Print all request parameters

		return "redirect:/emplist";

	}
//	@RequestMapping(value="/update",method= {RequestMethod.POST})
//	@GetMapping("/editemp")
//	public String updateEmployeePre(Model model,@RequestParam("empid") Long id) {
////		System.out.println(emp.toString());
//		Employee employee = employeeService.getEmployeeById(id);
//        model.addAttribute("emp", employee);
//		System.out.println(id);
//		return "EditEmployeeForm";
//	}

//	@RequestMapping(value="/update",method = {RequestMethod.POST})
//	public String updateEmployeeDetail(Model model,@ModelAttribute("updateEmp") Employee employee) {
//		System.out.println(employee.toString());
//		return "redirect:/EditEmployeeForm";
//	}
//	
	@GetMapping(value = "/deleteEmployee")
	public String deleteEmployee(Model model, @RequestParam("empid") Long id) {
		System.out.println(id);
		employeeService.deleteEmployee(id);
		return "redirect:/emplist";
	}

	@GetMapping("/editemp")
	public String updateEmployee(Model model, @RequestParam("empid") Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee); // Changed attribute name to "employee"
		return "EditEmployeeForm";
	}

	@PostMapping("/update")
	public String updateEmployeeDetail(@ModelAttribute("employee") Employee employee) {
		System.out.println(employee.toString());
		employeeService.UpdateEmployee(employee);
		return "redirect:/emplist"; // Redirect to employee list page
	}

	@GetMapping("/roles")
	public ModelAndView roles(Model model) {
		ModelAndView mvm = new ModelAndView("AddRole");
		List<Role> roles = roleService.getAllRole();
		List<Employee> emps = employeeService.getAllEmployee();
		List<Department> depts = deptService.getAllDepartments();
		System.out.println(roles);
		mvm.addObject("roles", roles);
		mvm.addObject("emps", emps);
		mvm.addObject("departments", depts);
		return mvm;
	}

//	@PostMapping("/addRole")
//	public String addRole(@RequestParam("roleName") String roleName, @RequestParam("roleDescription") String roleDesc,
//			Model model) {
//		System.out.println(roleName + " " + roleDesc);
//		roleService.createRole(roleName, roleDesc);
//		List<Role> roles = roleService.getAllRole();
//		List<Employee> emps = employeeService.getAllEmployee();
//		model.addAttribute("emps", emps);
//		model.addAttribute("roles", roles);
//		return "AddRole";
//	}

	/*
	 * @PostMapping("/assignRole") public String
	 * assignRole(@RequestParam("employeeId") Long empId, @RequestParam("roleId")
	 * Long roleId) { roleService.assignRoleToEmployee(empId, roleId); return
	 * "AddRole"; }
	 */

	/*
	 * @PostMapping("/assignRole") public ResponseEntity<?>
	 * assignRole(@RequestParam("employeeId") Long empId, @RequestParam("roleId")
	 * Long roleId) { try { roleService.assignRoleToEmployee(empId, roleId); return
	 * ResponseEntity.ok("Role assigned successfully"); } catch (Exception e) {
	 * return ResponseEntity.badRequest().body("Error assigning role: " +
	 * e.getMessage()); } }
	 */
	@PostMapping("/assignRole")
	@ResponseBody
	public ResponseEntity<?> assignRole(@RequestParam("employeeId") Long empId, @RequestParam("roleId") Long roleId) {
		Map<String, Object> response = new HashMap<>();
		try {
			roleService.assignRoleToEmployee(empId, roleId);
			response.put("success", true);
			response.put("message", "Role assigned successfully");
			response.put("redirectUrl", "/roles"); // Add redirect URL to response
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Error assigning role: " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	/*
	 * Add Role Method
	 * 
	 */
	@PostMapping("/addRole")
	@ResponseBody
	public ResponseEntity<?> addRole(@RequestParam("roleName") String roleName,
			@RequestParam("roleDescription") String roleDesc) {
		Map<String, Object> response = new HashMap<>();

		try {
			// Validate input
			if (roleName == null || roleName.trim().isEmpty()) {
				throw new IllegalArgumentException("Role name is required");
			}

			// Create role
			roleService.createRole(roleName, roleDesc);

			// Get updated lists
//			List<Role> roles = roleService.getAllRole();
//			List<Employee> emps = employeeService.getAllEmployee();

			// Prepare success response
			response.put("success", true);
			response.put("message", "Role added successfully");
//			response.put("roles", roles);
//			response.put("employees", emps);
			response.put("redirectUrl", "/roles");

			return ResponseEntity.ok(response);

		} catch (IllegalArgumentException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Error adding role: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/*
	 * Assign Department to Employee
	 * 
	 */
	@PostMapping("/assignEmployeeToDepartment")
	@ResponseBody
	public ResponseEntity<?> assignEmployeeToDepartment(@RequestParam("employeeId") Long empId,
			@RequestParam("departmentId") String deptId) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Input validation
			if (empId == null || deptId == null) {
				throw new IllegalArgumentException("Employee ID and Department ID are required");
			}

			// Assign department to employee
			deptService.assignDepartmentToEmplyoee(deptId, empId);

			// Prepare success response
			response.put("success", true);
			response.put("message", "Employee assigned to department successfully");
			response.put("redirectUrl", "/roles");
			return ResponseEntity.ok(response);

		} catch (EntityNotFoundException e) {
			// Handle case when employee or department not found
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

		} catch (IllegalArgumentException e) {
			// Handle invalid input
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);

		} catch (Exception e) {
			// Handle other exceptions
			response.put("success", false);
			response.put("message", "Error assigning department: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
