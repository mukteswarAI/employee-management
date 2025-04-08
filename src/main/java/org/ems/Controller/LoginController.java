package org.ems.Controller;

import org.ems.employee.model.Employee;
import org.ems.employee.repository.EmployeeRepository;
import org.ems.employee.service.EmployeeService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

import java.util.Map;

@Controller
public class LoginController {

	private final EmployeeService employeeService;

	public LoginController(EmployeeService employeeService) {
//		super();
		this.employeeService = employeeService;
	}

	@GetMapping({"/", "/login"})
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody // Add this annotation
	public String processLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		// ... (your authentication logic) ...
//		System.out.println(email + " " + password);

		Employee authenticatedEmployee = employeeService.authenticateEmployee(email, password);
//		System.out.println(authenticatedEmployee.getEmployeeName());

		if (authenticatedEmployee != null) {
			// ...
			String role = authenticatedEmployee.getRole().getRoleName();

			if ("Admin".equalsIgnoreCase(role)) {
				
				session.setAttribute("loggedInEmployee", authenticatedEmployee);
				return "redirect:/admin"; // Return redirect string
			} else if ("Hr".equalsIgnoreCase(role)) {
				session.setAttribute("loggedInEmployee", authenticatedEmployee);

				return "redirect:/hr";
			} else {
				session.setAttribute("loggedInEmployee", authenticatedEmployee);

				return "redirect:/employee"; // Return redirect string
			}
		} else {
			return "login?error"; // Return a string indicating login failure
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/ForgetPass")
	public String forgetPass() {
		return "forgetpass";
	}

	@PostMapping("/email-verify")
	public ResponseEntity<?> emailVerify(@RequestParam("email") String email) {
		Employee employeeByEmail = employeeService.getEmployeeByEmail(email);

		if (employeeByEmail != null) {
			String emailOtp = employeeService.sendEmailOtp(email);
			return ResponseEntity.ok().body(Map.of("status", "success", "message", "OTP sent!", "otp", emailOtp));
		} else {
			return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "User not found!"));
		}
	}

	@GetMapping("/otp-verify")
	public String otpVerifyPage(@RequestParam("email") String email,Model model) {
		model.addAttribute("email", email);
		return "otpVerify";
	}

	@PostMapping("/otp-verifying")
	public ResponseEntity<?> otpVerify(@RequestParam("email") String email, @RequestParam("otp") String otp) {
		System.out.println(email + " " + otp);
		boolean verified = employeeService.verifyOtp(email, otp);

		if (verified) {
			return ResponseEntity.ok().body(Map.of(
					"status", "success",
					"message", "OTP verified successfully!"
			));
		} else {
			return ResponseEntity.badRequest().body(Map.of(
					"status", "error",
					"message", "Invalid or expired OTP!"
			));
		}
	}


	@GetMapping("reset-pass")
	public String resetPass(@RequestParam("email") String email, Model model) {
		model.addAttribute("email", email);
		return "resetPass";
	}


	@PostMapping("/reset-pass")
	public ResponseEntity<?> resetPass(@RequestParam("email") String email, @RequestParam("password") String password) {
		Employee employee = employeeService.getEmployeeByEmail(email);

		if (employee == null) {
			return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "User not found!"));
		}

		// Encrypt the password before saving
//		String encodedPassword = passwordEncoder.encode(password);
		employee.setPassword(password);

		// Save updated employee
		employeeService.updateEmployeePassword(email,password);

		return ResponseEntity.ok().body(Map.of("status", "success", "message", "Password reset successfully!"));
	}
	

}
