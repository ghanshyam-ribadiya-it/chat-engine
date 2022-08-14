package com.automation.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automation.chat.model.EmployeeCustomerConversion;

@Repository
public interface EmployeeCustomerConversionRepository extends JpaRepository<EmployeeCustomerConversion, Long> {

	public List<EmployeeCustomerConversion> findByReplyByCustomer(Boolean replyByCustomer);

}
