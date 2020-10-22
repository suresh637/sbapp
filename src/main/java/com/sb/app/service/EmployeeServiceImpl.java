package com.sb.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.app.models.Employee;
import com.sb.app.persistance.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
    public List<Employee> getEmployees() {
		List<Employee> empLst = employeeRepository.findAll();
        return empLst;
    }
	
    @Override
    public Optional<Employee> getEmployeeById(long empid) {
        return employeeRepository.findById(empid);
    }
    @Override
    public Employee addNewEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }
    @Override
    public Employee updateEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }
    
    @Override
    public void deleteEmployeeById(@NotNull Long employeeId) {
    	employeeRepository.deleteById((long) employeeId);
    }
    @Override
    public void deleteAllEmployees() {
    	employeeRepository.deleteAll();
    }

	@Override
	public List<Employee> createEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> createBulkEmployees(List<Employee> empList) {
		Iterable<Employee> iterable = empList;
		List<Employee> savedEmployees = null;
		savedEmployees = employeeRepository.saveAll(iterable);
		return savedEmployees;
	}
	
	
	
}
