package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Employee;
import com.demo.model.TaxDeductionResponse;
import com.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/add")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee) {
		employeeService.saveOrUpdate(employee);
		return new ResponseEntity<>("Employee details added successfully", HttpStatus.OK);
    }

    @GetMapping("/tax-deduction/{employeeId}")
    public ResponseEntity<TaxDeductionResponse> getTaxDeduction(@PathVariable Integer employeeId) {
    	Employee employee = employeeService.getEmployeeById(employeeId);
    	if (employee == null) {
            return ResponseEntity.notFound().build();
        }
    	
    	double yearlySalary = calculateYearlySalary(employee);

        double taxAmount = calculateTax(yearlySalary);
        double cessAmount = calculateCess(yearlySalary);

        TaxDeductionResponse response = new TaxDeductionResponse();
        response.setEmployeeId(employee.getEmployeeId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setYearlySalary(yearlySalary);
        response.setTaxAmount(taxAmount);
        response.setCessAmount(cessAmount);

        return ResponseEntity.ok(response);
    }

    private double calculateYearlySalary(Employee employee) {
        return employee.getSalary() * 12;
    }

    private double calculateTax(double yearlySalary) {
        if (yearlySalary <= 250000) {
            return 0;
        } else if (yearlySalary <= 500000) {
            return 0.05 * (yearlySalary - 250000);
        } else if (yearlySalary <= 1000000) {
            return 0.1 * (yearlySalary - 500000) + 12500;
        } else {
            return 0.2 * (yearlySalary - 1000000) + 12500 + 50000;
        }
    }

    private double calculateCess(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return 0.02 * (yearlySalary - 2500000);
        } else {
            return 0;
        }
    }
    
}
