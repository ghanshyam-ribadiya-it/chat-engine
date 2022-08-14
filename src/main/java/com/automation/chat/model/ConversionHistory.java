package com.automation.chat.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "conversion_history")
@Getter
@Setter
public class ConversionHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "employee_customer_conversion_id", nullable = false)
	private EmployeeCustomerConversion employeeCustomerConversion;

	@Column(name = "message_type", nullable = false)
	private String messageType;

	@ManyToOne
	@JoinColumn(name = "message_template_id")
	private MessageTemplate messageTemplate;

	@Column(name = "message", nullable = false)
	private String message;

	@Generated(GenerationTime.INSERT)
	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdAt;
}
