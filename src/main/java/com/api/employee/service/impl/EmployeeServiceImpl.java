package com.api.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.employee.model.Employee;
import com.api.employee.repository.EmployeeRepository;
import com.api.employee.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletedEmployee(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getListEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
