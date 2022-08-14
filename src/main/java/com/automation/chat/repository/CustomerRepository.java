package com.automation.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automation.chat.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
