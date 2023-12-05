package com.bootproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootproject.entity.Employee;
import com.bootproject.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public void saveEmployee(Employee employee)
	{
		employeeRepository.save(employee);
		
	}

	
	public List<Employee> getEmployeeList() {
		
		return employeeRepository.findAll();
	}


	
	public Employee getEmployeeDetails(int eid) {
		
		return employeeRepository.findById(eid).orElse(null);
	}


	
	public List<Employee> getEmployeeListByDepartment(String department) {
	
		return employeeRepository.findByDepartment(department);
	}


	
//	public List<Employee> getEmployeeListBySalary(int sal1, int sal2) {
//		
//		return employeeRepository.findBySalary(sal1,sal2);
//	}


	
	public void updateEmployee(Employee employeen) {
		employeeRepository.save(employeen);
		
	}


	
	public void deleteEmployeeByDepartment(String department) {
		employeeRepository.deleteByDepartment(department);
		
	}



	public void deleteEmployeeById(int eid) {
		employeeRepository.deleteById(eid);
		
	}



}
