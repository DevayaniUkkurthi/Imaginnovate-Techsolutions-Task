package com.imaginnovatetech.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovatetech.model.Employee;
import com.imaginnovatetech.service.TaxCalculationService;

import jakarta.validation.Valid;
import java.math.BigDecimal;


@RestController
@Validated

public class EmployeeController {
	
	@Autowired
    private TaxCalculationService taxCalculationService;

	
	public ResponseEntity<String> createEmployee(@Valid  Employee employee){
		
		
		return new ResponseEntity<>("Employee Details saved successfully",HttpStatus.CREATED);
		
	}
	
	@GetMapping("/employeelist")
	public List<Employee> getAllEmployees(){
		List<String> PhoneNumemp1=new ArrayList<>();
		PhoneNumemp1.add("1234567891");
		PhoneNumemp1.add("2234567891");
		List<String> PhoneNumemp2=new ArrayList<>();
		PhoneNumemp2.add("7036700486");
		PhoneNumemp2.add("9951291106");
		
		Employee emp1=new Employee(1L,"John","Robert","john@imgtech.com",PhoneNumemp1,LocalDate.now(),50000.0);
		Employee emp2=new Employee(2L,"Stella","Raj","stella@imgtech.com",PhoneNumemp2,LocalDate.now(),7000000.0);

		List<Employee> emplist=new ArrayList<Employee>();
		emplist.add(emp1);
		emplist.add(emp2);

		
		
		return emplist;
		
	}
	
	
    @GetMapping("/employees/{employeeId}/tax")
    public ResponseEntity<BigDecimal> calculateTax(@PathVariable Long employeeId) {
        // Fetch employee details from the database
        Employee employee = null;// fetch employee by employeeId from database

        BigDecimal taxAmount = taxCalculationService.calculateTax(employee);
        return new ResponseEntity<>(taxAmount, HttpStatus.OK);
    }


}
