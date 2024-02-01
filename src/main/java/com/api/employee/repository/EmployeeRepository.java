package com.api.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>  {
	
	// check name uniqe
		@Query("SELECT COUNT(u) FROM Employee u WHERE u.nama=?1")
		public Integer countNameEmployee(String name);
	
}
