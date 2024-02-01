package com.api.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.common.beans.beans.EmployeeRequest;
import com.api.common.beans.beans.EmployeeResponse;
import com.api.common.beans.beans.Request;
import com.api.common.beans.beans.Response;
import com.api.common.beans.constant.ApiResponse;
import com.api.employee.service.IEmployeeService;

import io.micrometer.common.util.StringUtils;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object listGroup(@RequestBody Request<EmployeeRequest> rq ){
    	Response<List<EmployeeResponse>> response = new Response<>();
		
		List<EmployeeResponse> employees = employeeService.getListEmployee();
		if (employees.size() > 0) {
			response.setData(employees);
			response.setStatusResponse(ApiResponse.SUCCESS);
		}else {
			response.setStatusResponse(ApiResponse.DATA_NOT_FOUND);
		}
		
		if (ObjectUtils.isEmpty(rq.getRequestHeader()) || ObjectUtils.isEmpty(rq.getRequestHeader().getChanel())) {
			return response.getData();
		}else {
			return response;
		}
    }
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> createEmployee(@RequestBody Request<EmployeeRequest> rq ){
    	Response<String> response = new Response<>();
		
		String rs = employeeService.saveEmployee(rq.getRequestPayload());
		if (StringUtils.isEmpty(rs)) {
			response.setStatusResponse(ApiResponse.SUCCESS);
		}else {
			response.setStatusResponse(ApiResponse.FAILED);
		}
    	return response;
    }
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> updateEmployee(@RequestBody Request<EmployeeRequest> rq ){
    	Response<String> response = new Response<>();
		
		String rs = employeeService.updateEmployee(rq.getRequestPayload());
		if (StringUtils.isEmpty(rs)) {
			response.setStatusResponse(ApiResponse.SUCCESS);
		}else {
			response.setStatusResponse(ApiResponse.FAILED);
		}
    	return response;
    }
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> deleteEmployee(@RequestBody Request<EmployeeRequest> rq ){
    	Response<String> response = new Response<>();
		
		String rs = employeeService.deletedEmployee(rq.getRequestPayload().getGroupId());
		if (StringUtils.isEmpty(rs)) {
			response.setStatusResponse(ApiResponse.SUCCESS);
		}else {
			response.setStatusResponse(ApiResponse.FAILED);
		}
    	return response;
    }
	
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object detailEmployee(@RequestBody Request<EmployeeRequest> rq ){
    	Response<EmployeeResponse> response = new Response<>();
		
    	EmployeeResponse rs = employeeService.getEmployeById(rq.getRequestPayload().getGroupId());
		if (rs != null) {
			response.setData(rs);
			response.setStatusResponse(ApiResponse.SUCCESS);
		}else {
			response.setStatusResponse(ApiResponse.DATA_NOT_FOUND);
		}
		if (ObjectUtils.isEmpty(rq.getRequestHeader()) || ObjectUtils.isEmpty(rq.getRequestHeader().getChanel())) {
			return response.getData();
		}else {
			return response;
		}
    }
	
	
}
