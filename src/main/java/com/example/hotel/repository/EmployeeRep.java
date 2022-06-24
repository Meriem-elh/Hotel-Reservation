package com.example.hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entity.Employee;


public interface EmployeeRep extends JpaRepository<Employee, Long> {
Employee findByEmail(String email);
void deleteById(Long id);

	Employee findByUsername(String username);
	Page<Employee> findByUsernameContains(String kw,Pageable pageable);

}
