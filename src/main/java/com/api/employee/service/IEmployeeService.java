package com.api.employee.service;

import java.util.List;

import com.api.common.beans.beans.EmployeeRequest;
import com.api.common.beans.beans.EmployeeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IEmployeeService {

	public String saveEmployee(EmployeeRequest employee);
	
	public String updateEmployee(EmployeeRequest employee) throws JsonProcessingException;
	
	public String deletedEmployee(Long id);
	
	public List<EmployeeResponse> getListEmployee();
	
	public EmployeeResponse getEmployeById(Long id);
	
	
}
