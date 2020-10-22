/**
 * 
 */
package com.sb.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.app.exception.ResourceNotFoundException;
import com.sb.app.models.Employee;
import com.sb.app.persistance.EmployeeRepository;
import com.sb.app.service.EmployeeServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author User
 *
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@GetMapping(value = "/count")
	public String getTotalEmployeesCount() {
		
		List<Employee> employees = employeeService.getEmployees();

		return "Total Employees count : " + employees.size();
	}
	
   @ApiOperation(value = "Get list of employees in the System ", response = Iterable.class, tags = "getEmployees")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<Employee>> getAllEmployees() {
	   
		try {
			
			List<Employee> employees = new ArrayList<Employee>();
			employees = employeeService.getEmployees();

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employees, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
   
   @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Employee> getEmployeeById(@NotNull @PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {

		final Employee emp = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + employeeId));

		return new ResponseEntity(emp, HttpStatus.OK);
	}
   
   

	@RequestMapping(value = "/employeeslist", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Employee> createBlukEmployees(@NotNull @Valid @RequestBody final List<Employee> empList) {
		
		Iterable<Employee> iterable = empList;
		List<Employee> savedEmployees = null;
		

		try {
			savedEmployees = employeeService.createBulkEmployees(empList);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(savedEmployees, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Employee> createEmployee(@NotNull @Valid @RequestBody Employee employee) {
		
		Employee newEmployee;
		
		try {
			newEmployee = employeeService.addNewEmployee(employee);
			
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(newEmployee,HttpStatus.CREATED);
	}

	@RequestMapping(value ="/employees/{id}" , method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Employee> updateEmployee(@NotNull @PathVariable(value = "id") Long EmployeeId,
			@Valid @RequestBody Employee userDetails) throws ResourceNotFoundException {

		Employee user = employeeService.getEmployeeById(EmployeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + EmployeeId));
		
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());

		final Employee updatedUser = employeeService.updateEmployee(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@NotNull @PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {

		Employee Employee =  employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + employeeId));

		employeeService.deleteEmployeeById(employeeId);
		
		return new ResponseEntity("Deleted " + employeeId + " successfuly..", HttpStatus.OK);
	}


}
