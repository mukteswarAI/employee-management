package org.ems.report;

import java.util.Map;

public class EmployeeStatisticsDTO {
	private long totalEmployees;
	private long activeEmployees;
	private long inactiveEmployees;
	private Map<String, Long> employeesByDepartment;
	private Map<String, Long> employeesByRole;
}
