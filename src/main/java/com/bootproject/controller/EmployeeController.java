package com.bootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootproject.entity.Employee;
import com.bootproject.service.EmployeeService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("employee")
public class EmployeeController 
{
	@Autowired 
	private EmployeeService employeeService;
	
	//url-->http://localhost:8080/employee/create
		@PostMapping("create")
		public ResponseEntity<Employee> createResource(@RequestBody Employee employee)
		{
			employeeService.saveEmployee(employee);
			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		}
		
		//url-->http://localhost:8080/employee/list
		@GetMapping("list")
		public ResponseEntity<List<Employee>> getList()
		{
			List<Employee> employeeList=employeeService.getEmployeeList();
			return ResponseEntity.status(HttpStatus.OK).body(employeeList);
		}
		//url-->http://localhost:8080/employee/details?eid=101
//		@GetMapping("details")
//		public ResponseEntity<Employee> getDetails(@RequestParam("eid") int eid)
//		{
//			Employee employee=employeeService.getEmployeeDetails(eid);
//			if(employee==null)
//			{
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
//			}
//			return ResponseEntity.status(HttpStatus.OK).body(employee);
//		}
		
		//url-->http://localhost:8080/employee/details/101
		@GetMapping("details/{eid}")
		public ResponseEntity<Employee> getDetails(@PathVariable("eid") int id)
		{
			Employee employee=employeeService.getEmployeeDetails(id);
			if(employee==null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
			}
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		}
		//url-->http://localhost:8080/employee/list/bydept?dept=Sales
		@GetMapping("list/bydept")
		public ResponseEntity<List<Employee>> getListByDepartment(@RequestParam("dept") String department)
		{
			List<Employee> employeeList=employeeService.getEmployeeListByDepartment(department);
			if(employeeList.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(employeeList);
		}
		
		//url-->http://localhost:8080/employee/list/bydept?salary1=50000&salary2=80000
//		@GetMapping("list/bysalary")
//		public ResponseEntity<List<Employee>> getListBySalary(@RequestParam("salary1") int sal1,@RequestParam("salary2") int sal2)
//		{
//			List<Employee> employeeList=employeeService.getEmployeeListBySalary(sal1,sal2);
//			if(employeeList.isEmpty())
//			{
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//			return ResponseEntity.status(HttpStatus.OK).body(employeeList);
//		}

		//url-->http://localhost:8080/employee/update?eid=104
		@PutMapping("update")
		public ResponseEntity<Employee> updateResource(@RequestParam("eid") int eid,@RequestBody Employee employeen)
		{
			Employee employeeo=employeeService.getEmployeeDetails(eid);
			if(employeeo!=null)
			{
				employeen.setEid(eid);
				employeeService.updateEmployee(employeen);
				return ResponseEntity.status(HttpStatus.OK).body(employeen);
			}
			employeeService.saveEmployee(employeen);
			return ResponseEntity.status(HttpStatus.CREATED).body(employeen);
		}
		
		@PatchMapping("pupdate")
		public ResponseEntity<Employee> pupdateResource(@RequestParam("eid") int eid,@RequestBody Employee employeen)
		{
			Employee employeeo=employeeService.getEmployeeDetails(eid);
			if(employeeo==null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			if(employeen.getName()!=null)
				employeeo.setName(employeen.getName());
			if(employeen.getDepartment()!=null)
				employeeo.setDepartment(employeen.getDepartment());
			if(employeen.getSalary()!=0)
				employeeo.setSalary(employeen.getSalary());
			employeeo.setEid(eid);
			employeeService.updateEmployee(employeeo);
			return ResponseEntity.ok(employeeo);	
		}
		//url--> http://localhost:8080/employee/delete/byid?eid=${eid}
		@DeleteMapping("delete/byid")
		public ResponseEntity<Employee> deleteById(@RequestParam("eid") int eid)
		{
			Employee employee=employeeService.getEmployeeDetails(eid);
			if(employee==null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
			}
			employeeService.deleteEmployeeById(eid);
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		}
		
		
		@DeleteMapping("delete/bydept")
		public ResponseEntity<List<Employee>> deleteByDepartment(@RequestParam("dept") String department)
		{
			List<Employee> employeeList=employeeService.getEmployeeListByDepartment(department);
			if(employeeList.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			employeeService.deleteEmployeeByDepartment(department);
			return ResponseEntity.status(HttpStatus.OK).body(employeeList);
		}
	}

