package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public void saveOrUpdate(Employee employee) 
	{
		employeeRepository.save(employee);
	}
	
	public Employee getEmployeeById(int id) 
	{
	return employeeRepository.findById(id).get();
	}
}
