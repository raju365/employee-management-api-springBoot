package com.bootproject.service;

import java.util.List;

import com.bootproject.entity.Employee;

public interface EmployeeService{

	void saveEmployee(Employee employee);

	List<Employee> getEmployeeList();

	Employee getEmployeeDetails(int eid);

	List<Employee> getEmployeeListByDepartment(String department);

//	List<Employee> getEmployeeListBySalary(int sal1, int sal2);

	void updateEmployee(Employee employeen);

	void deleteEmployeeByDepartment(String department);

	void deleteEmployeeById(int eid);

}
