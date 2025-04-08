package org.ems.department.service;

import org.ems.department.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DepartmentService {

    void addDepartment(Department department);
    List<Department> getAllDepartments();

    void updateDepartment(Department department);

    Department getDepartmentById(String id);

    void deleteDepartment(String id);
    void assignDepartmentToEmplyoee(String departmentId,Long employeeId);

   long getTotalDeptCount();
}
