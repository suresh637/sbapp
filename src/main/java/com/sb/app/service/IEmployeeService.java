package com.sb.app.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.sb.app.models.Employee;

public interface IEmployeeService {
	
	public List<Employee> createEmployee(Employee emp);
	public List<Employee> createBulkEmployees(List<Employee> empList);

    public List<Employee> getEmployees();
    public Optional<Employee> getEmployeeById(long empid);
    public Employee addNewEmployee(Employee emp);
    public Employee updateEmployee(Employee emp);
    public void deleteEmployeeById(@NotNull Long employeeId);
    public void deleteAllEmployees();
}