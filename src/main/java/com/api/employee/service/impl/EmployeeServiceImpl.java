package com.api.employee.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.common.beans.beans.EmployeeRequest;
import com.api.common.beans.beans.EmployeeResponse;
import com.api.common.beans.constant.AppConstants;
import com.api.employee.model.Employee;
import com.api.employee.repository.EmployeeRepository;
import com.api.employee.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(EmployeeRequest rq) {
		String rs = null;
		Employee employee = new Employee();
		employee.setNama(rq.getNama());
		employee.setTglLahir(rq.getTglLahir());
		employee.setStatus(1);
		employee.setGroupId(Long.valueOf(rq.getGroupId()));
		employee.setSaldoId(Long.valueOf(rq.getSaldoId()));
		employee.setCreatedAt(new Date());
		
		if (employeeRepository.countNameEmployee(employee.getNama()).equals(0)) {
			employeeRepository.save(employee);
		}else {
			rs = AppConstants.ALREADY_EXIST;
			logger.error(rs);
		}
		return rs;
		
	}

	@Override
	public String updateEmployee(EmployeeRequest rq) {
		String rs = null;

		try {
			Employee employee = employeeRepository.findById(rq.getId()).orElseThrow();
			employee.setNama(rq.getNama());
			employee.setUpdatedAt(new Date());
			employee.setTglLahir(rq.getTglLahir());
			employeeRepository.save(employee);
		} catch (Exception e) {
			rs = AppConstants.DATA_NOT_FOUND;
			logger.error(rs);
		}
		return rs;
	}

	@Override
	public String deletedEmployee(Long id) {
		String rs = null;

		try {
			Employee employee = employeeRepository.findById(id).orElseThrow();
			employee.setStatus(0);
			employeeRepository.save(employee);
		} catch (Exception e) {
			rs = AppConstants.DATA_NOT_FOUND;
			logger.error(rs);
		}
		return rs;
		
	}

	@Override
	public List<EmployeeResponse> getListEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream()
				.map(e -> new EmployeeResponse(e.getId(), e.getNama(), e.getTglLahir(), e.getStatus()))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeResponse getEmployeById(Long id) {
		EmployeeResponse rs = null;
		try {
			Employee e = employeeRepository.findById(id).orElseThrow(); 
			rs = new EmployeeResponse(e.getId(), e.getNama(), e.getTglLahir(), e.getStatus(), e.getGroupId(), e.getSaldoId());
		} catch (Exception e) {
			logger.error(AppConstants.DATA_NOT_FOUND);
		}
		return rs;
	}

}
