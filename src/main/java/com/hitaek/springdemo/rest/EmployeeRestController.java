package com.hitaek.springdemo.rest;

import com.hitaek.springdemo.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private List<Employee> theEmployees;

	// @PostConstruct to load the employee data

	@PostConstruct
	public void loadEmployeeSampleData(){
		theEmployees = new ArrayList<>();

		theEmployees.add(new Employee("Gyotaek", "lee"));
		theEmployees.add(new Employee("Ted", "mosby"));
		theEmployees.add(new Employee("Barny", "Stinson"));

	}

	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return theEmployees;
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		if(employeeId >= theEmployees.size() || employeeId < 0){
			throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
		}
		return theEmployees.get(employeeId);
	}


	
}
