package com.api.employee.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.api.common.beans.beans.EmployeeRequest;
import com.api.common.beans.beans.EmployeeResponse;
import com.api.common.beans.beans.Request;
import com.api.common.beans.beans.Response;
import com.api.common.beans.constant.ApiResponse;
import com.api.common.beans.constant.AppConstants;
import com.api.employee.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@BeforeEach
	void setUp() {
		employeeRepository.deleteAll();
	}
	
	
	void saveEmployee() throws JsonProcessingException, Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.FORMAT_DD_MM_YYYY);
		ObjectMapper mapper = new ObjectMapper();
		Request<EmployeeRequest> rq = new Request<EmployeeRequest>();
		EmployeeRequest rqPayload = new EmployeeRequest();
		
		Date date = sdf.parse("11-09-1997");
		rqPayload.setNama("Syahrul Ilham Mu");
		rqPayload.setTglLahir(date);
		rqPayload.setStatus(1);
		rqPayload.setGroupId(Long.valueOf(1));
		rqPayload.setSaldoId(Long.valueOf(1));
		rq.setRequestPayload(rqPayload);
		
		
		mockMvc.perform(
                post("/employee/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rq))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            Response<String> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals(response.getStatus(), ApiResponse.SUCCESS.getCode().toString());
        });
	}
	
	@Test
	void listEmployee() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Request<EmployeeRequest> rq = new Request<EmployeeRequest>();
		
		mockMvc.perform(
                post("/employee/list")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rq))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            Response<List<EmployeeResponse>> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals(response.getStatus(), ApiResponse.SUCCESS.getCode().toString());
        });
	}
	
	
	

}
