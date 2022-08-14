package com.automation.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automation.chat.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
