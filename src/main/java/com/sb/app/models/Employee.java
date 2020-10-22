package com.sb.app.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "employee")
public class Employee extends AuditModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;


    @Column(name = "first_name" , nullable = false)
	private String firstName;

    @Column(name = "last_name", nullable = false)
	private String lastName ;
    
    @Column(name = "salary", nullable = false)
	private Double salary;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "employee",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Address> addresses;
    
	public Employee(String firstName, String lastName, Double salary, List<Address> addresses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.addresses = addresses;
	}

	public Employee() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstNname=" + firstName + ", lastName=" + lastName + ", salary=" + salary
				+ ", addresses=" + addresses + "]";
	}

}
