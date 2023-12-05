package com.bootproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootproject.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByDepartment(String department);

//	List<Employee> findBySalary(int sal1, int sal2);

	void deleteByDepartment(String department);
	

}
