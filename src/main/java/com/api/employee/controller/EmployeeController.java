package com.api.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.api.employee.service.IEmployeeService;

@RestController
public class EmployeeController {

	
	@Autowired
	private IEmployeeService employeeService;
	
	
}
