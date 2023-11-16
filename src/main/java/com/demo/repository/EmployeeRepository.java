package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
