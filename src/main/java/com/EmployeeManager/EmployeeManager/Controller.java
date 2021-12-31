package com.EmployeeManager.EmployeeManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

	@Autowired
	private EmployeeService service;
	
	
	// Using RequestMapping instead of POST allows me to make the /employee/register as both a POST and PUT request
	// Will return the desired message and 200 code
	@RequestMapping(value={"/employee/register"},method={RequestMethod.POST,RequestMethod.PUT})
	public ResponseEntity<String> create(@RequestBody Employee employee) {
		service.createEmployee(employee);
		Integer createdId = employee.getId();
		String msg = "Employee data registered Employee id " + createdId.toString();
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	// Simple GET request. If the employee ID exists, it's data will be returned with a 200 code. Otherwise will return 404 Not Found
	@GetMapping("/employee/{employeeID}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer employeeID) {
		try {
            Employee employee = service.getEmployee(employeeID);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
	}

	// Returns a list of all employees with a 200 code
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAll() {
		List<Employee> employees = service.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	// Calls the service method to update the employee ID with the new data passed through the body of the request
	// returns desired message and 200 code
	@PutMapping("/employee/update/{employeeID}")
	public ResponseEntity<String> edit(@RequestBody Employee employee, @PathVariable Integer employeeID) {
		service.editEmployee(employeeID, employee);
		String msg = "Employee data updated Employee id " + employeeID;
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	/*
	 *  Exports all employee data. File name reads "employeeDownload" followed by a date time stamp
	 *  If this is going to be a repeated download, it is smart to differentiate when they are downloaded with time stamp
	 *  Uses Service writeToCsv method
	 */
	@GetMapping("/employee/exportEmployeeData")
	public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        String date = new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new Date());
		String path = "employeeDownload_"+date+".csv\"";
		servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\""+path);
        service.writeToCsv(servletResponse.getWriter());
    }


	
}
  