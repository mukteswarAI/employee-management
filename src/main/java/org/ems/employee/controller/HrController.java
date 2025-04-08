package org.ems.employee.controller;

import org.ems.employee.model.Employee;
import org.ems.employee.model.LeaveRequest;
import org.ems.employee.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HrController {

	private EmployeeService employeeService;
	public HrController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/hr")
	public String hrIndex() {
		return "hrindex";
	}

	@GetMapping("/Hr-profile")
	public String hrProfile(Model model){
		return "HrProfile";
	}


	@GetMapping("/Leave-list")
	public String hrLeaveList(Model model){
		List<LeaveRequest> allLeaveRequest = employeeService.getAllLeaveRequest();
		model.addAttribute("allLeaveRequest", allLeaveRequest);
		return "leaveList";
	}



	@GetMapping("/Leave-details")
	public String leaveDetails(@RequestParam("leaveId") long leaveId, Model model) {
		LeaveRequest leaveRequest = employeeService.getLeaveRequestById(leaveId);
		model.addAttribute("leaveForm", leaveRequest); // Pass LeaveRequest object
		return "leaveDetails"; // JSP page where form is rendered
	}

	@PostMapping("/Update-leave")
	public String leaveUpdate(@RequestParam("leaveId") long leaveId,@RequestParam("status") String status){

		System.out.println(status);

		LeaveRequest leaveRequestById = employeeService.getLeaveRequestById(leaveId);
		leaveRequestById.setStatus(status);
		employeeService.UpdateLeaveRequest(leaveRequestById);
		return "redirect:/Leave-list";
	}


}
