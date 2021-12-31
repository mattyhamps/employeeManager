package com.EmployeeManager.EmployeeManager;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// Uses repository findByID function - takes in integer and returns the employee
	public Employee getEmployee(Integer id) {
		return employeeRepository.findById(id).get();
	}
	// Uses repository findAll() function - no arguments and returns all employees from database in a list
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	// Uses repository save() function - takes in an employee and saves them to database
	public void createEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	/*
	 *  Uses repository findByID() to search for employeeID in database. Then uses other employee as argument to make the changes necessary
	 *  
	 *  This will replace all the details from the employee found in the database and replace them with the details of the employeeInfo variable
	 */
	public void editEmployee(Integer id, Employee employeeInfo) {
		if(employeeRepository.findById(id).isPresent())
		{
			Employee existingEmployee = employeeRepository.findById(id).get();
			existingEmployee.setFirstName(employeeInfo.getFirstName());
			existingEmployee.setLastName(employeeInfo.getLastName());
			existingEmployee.setDesignation(employeeInfo.getDesignation());
			existingEmployee.setDob(employeeInfo.getDob());
			existingEmployee.setIsManager(employeeInfo.getIsManager());
			existingEmployee.setManagerId(employeeInfo.getManagerId());
			existingEmployee.setDepartment(employeeInfo.getDepartment());
		}
	}
	
	//This method returns the employee's manager's first name - if the manager number does not exist, it will return an N/A
	public String getManagerFirstName(Employee employee) {
		try {
			return employeeRepository.getById(employee.getManagerId()).getFirstName();
		} catch (EntityNotFoundException e) {
			return "N/A";
		}
	}
     
	/*
	 *  Uses CSV Printer to write each employee with '|' delimiter as specified in requirements
	 *  Header matches requirements
	 *  Catches IOException if there was an error writing to CSV
	 */
	public void writeToCsv(Writer writer) {

        List<Employee> employees = employeeRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter('|').withHeader(new String[] {"EmployeeID","EmpFname","EmpLname","Designation","ManagerID","ManagerFName"}))) {
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getDesignation(), employee.getManagerId(), getManagerFirstName(employee));
            }
        } catch (IOException e) {
           System.out.println("Error While writing CSV ");
        }
    }
	
}
