package org.ems.department.service;


import org.ems.department.model.Department;
import org.ems.department.repository.DepartmentRepository;
import org.ems.employee.model.Employee;
import org.ems.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void addDepartment(Department department) {
        System.out.println(department.toString());
        departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void updateDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(String id) {
        Optional<Department> byId = departmentRepository.findById(id);
        return byId.get();
    }

    @Override
    public void deleteDepartment(String id) {
        Optional<Department> byId = departmentRepository.findById(id);
        departmentRepository.delete(byId.get());
    }


	@Override
	public void assignDepartmentToEmplyoee(String departmentId, Long employeeId) {
		// TODO Auto-generated method stub
//		Optional<Department> dept = departmentRepository.findById(departmentId);
		 Employee employee = employeeRepository.findById(employeeId)
		            .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
		            
		        Department department = departmentRepository.findById(departmentId)
		            .orElseThrow(() -> new EntityNotFoundException("Department not found"));
		            
		        employee.setDepartment(department);
		        employeeRepository.save(employee);
		
		
	}


	@Override
	public long getTotalDeptCount() {
		// TODO Auto-generated method stub
		return departmentRepository.countDepartments();
	}
	
    
}
