package org.ems.employee.service;

import java.util.List;
import java.util.Optional;

import org.ems.EmailConfig.EmailDetails;
import org.ems.EmailConfig.EmailService;
import org.ems.EmailConfig.OtpService;
import org.ems.employee.model.Employee;
import org.ems.employee.model.LeaveRequest;
import org.ems.employee.repository.EmployeeRepository;
import org.ems.employee.repository.LeaveRepository;
import org.ems.utils.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;

	private final LeaveRepository leaveRepository;

	@Autowired
	private EmailService emailService;

	private final OtpService otpService;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, LeaveRepository leaveRepository, OtpService otpService) {

		this.employeeRepository = employeeRepository;
		this.leaveRepository = leaveRepository;
        this.otpService = otpService;
    }

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		System.out.println(employee.toString());
		Employee saved = employeeRepository.save(employee);

		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(employee.getEmail());
		emailDetails.setSubject("Login Credentials");
//		Checking the Email Details
		System.out.println(emailDetails.toString());
//		Email Sending Details
		emailService.sendUserCredentials(emailDetails,saved);
//		return "done";
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = employeeRepository.findById(id);
		employeeRepository.delete(employee.get());

	}

	@Override
	public Employee getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

	@Override
	public void UpdateEmployee(Employee emp) {
		Employee empUpdated = new Employee();
		empUpdated.setEmployeeName(emp.getEmployeeName());
		empUpdated.setEmail(emp.getEmail());
		empUpdated.setAddress(emp.getAddress());
		empUpdated.setDob(emp.getDob());
		empUpdated.setPhoneNo(emp.getPhoneNo());
		empUpdated.setSalary(emp.getSalary());
		// TODO Auto-generated method stub
		employeeRepository.save(empUpdated);

	}

	@Override
	public List<LeaveRequest> getAllLeaveRequest() {
		return leaveRepository.findAll();
	}

	@Override
	public List<LeaveRequest> getLeaveRequestByEmployeeId(Long id) {
		return leaveRepository.findByEmpId(id);
	}

	@Override
	public LeaveRequest getLeaveRequestById(Long id) {
		return leaveRepository.findById(id).get();
	}

	@Override
	public void AddLeaveRequest(LeaveRequest leave) {
		LeaveRequest saved = leaveRepository.save(leave);
	}

	@Override
	public void UpdateLeaveRequest(LeaveRequest leave) {
		Optional<LeaveRequest> byId = leaveRepository.findById(leave.getLeaveId());
		byId.get().setStatus(leave.getStatus());
		leaveRepository.save(byId.get());

	}

	@Override
	public Employee authenticateEmployee(String email, String password) {
		// TODO Auto-generated method stub
		return employeeRepository.findByEmailAndPassword(email, password); // Use email

	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		Employee byEmail = employeeRepository.findByEmail(email);
		return byEmail;
	}

	@Override
	public String sendEmailOtp(String email) {
		String generateOtp = OtpGenerator.generateOtp();
		EmailDetails emailDetails=new EmailDetails();
		emailDetails.setRecipient(email);
		emailDetails.setSubject("Reset Password Verification");
		emailDetails.setOtp(generateOtp);

		emailService.sendOtpMail(emailDetails);
		otpService.storeOtp(email,generateOtp);

		return OtpGenerator.generateOtp();

	}

	@Override
	public boolean verifyOtp(String email, String otp) {
		boolean verifyStatus = otpService.verifyOtp(email, otp);
		return verifyStatus;
	}

	@Override
	public Employee updateEmployeePassword(String email, String newPassword) {
		Employee byEmail = employeeRepository.findByEmail(email);
		byEmail.setPassword(newPassword);
		Employee saved = employeeRepository.save(byEmail);
		return saved;
	}

	@Override
	public long getTotalEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.countEmployees();
	}

//	@Override
//	public Employee getEmployeeById(Long id) {
//		return employeeRepository.getById(id);
//	}
	

}
