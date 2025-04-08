package org.ems.employee.repository;

import org.ems.employee.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveRequest,Long> {

    public List<LeaveRequest> findByEmpId(Long empId);
}
