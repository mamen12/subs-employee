package com.api.employee.service;

import java.util.List;

import com.api.employee.model.Employee;

public interface IEmployeeService {

	public void saveEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);
	
	public void deletedEmployee(Long id);
	
	public List<Employee> getListEmployee();
	
	public Employee getEmployeById(Long id);
	
	
}
