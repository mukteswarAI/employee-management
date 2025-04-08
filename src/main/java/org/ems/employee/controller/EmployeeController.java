package org.ems.employee.controller;

import java.util.List;

import org.ems.employee.model.Employee;
import org.ems.employee.model.LeaveRequest;
import org.ems.employee.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public String index() {
    	
        return "EmployeeDashboard";
    }

    @GetMapping("empprofile")
    public String empProfile(Model model,HttpSession session){
        Employee employee = (Employee) session.getAttribute("loggedInEmployee");
        Employee employeeById = employeeService.getEmployeeById(employee.getEmployeeId());
        model.addAttribute("emp", employeeById);
        return "employeeProfile";
    }

    @PostMapping("/Request-leave")
    public String createLeaveRequest(
            @RequestParam("empId") Long empId,
            @RequestParam("leaveType") String leaveType,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("contactNumber") String contactNumber,HttpSession session)
    {
        Employee loggedUser = (Employee)session.getAttribute("loggedInEmployee");
//    	System.out.println(empId + " " + leaveType + " " + description + " "+contactNumber);

        // Create LeaveRequest object
        LeaveRequest leaveRequest = new LeaveRequest();

        leaveRequest.setEmpId(empId);
        leaveRequest.setEmpName(loggedUser.getEmployeeName());
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setLeaveDescription(description); // Assuming `description` is mapped to `leaveDescription`
//      leaveRequest.setFromDate(fromDate);
//      leaveRequest.setToDate(toDate);
        leaveRequest.setStatus("Pending"); // Default status

        employeeService.AddLeaveRequest(leaveRequest);

        return "redirect:/Leave-form";
    }


    @GetMapping("/Leave-form")
    public String leaveForm(Model model){
        return "LeaveForm";
    }


    @GetMapping("/EmpLeave-list")
    public String empLeaveList(Model model, @RequestParam("empId") Long empId){
        List<LeaveRequest> leaveRequestByEmployeeId = employeeService.getLeaveRequestByEmployeeId(empId);
        model.addAttribute("leaveRequests", leaveRequestByEmployeeId);
        return "empLeaveList";
    }



}
