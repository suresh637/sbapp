package com.sb.app.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.app.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}