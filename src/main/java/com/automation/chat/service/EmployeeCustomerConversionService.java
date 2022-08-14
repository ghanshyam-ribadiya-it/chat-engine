package com.automation.chat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automation.chat.model.ConversionHistory;
import com.automation.chat.model.EmployeeCustomerConversion;
import com.automation.chat.model.MessageTemplate;
import com.automation.chat.repository.ConversionHistoryRepository;
import com.automation.chat.repository.EmployeeCustomerConversionRepository;
import com.automation.chat.repository.MessageTemplateRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeCustomerConversionService {

	@Autowired
	private EmployeeCustomerConversionRepository employeeCustomerConversionRepository;
	
	@Autowired
	private MessageTemplateRepository messageTemplateRepository;
	
	@Autowired
	private ConversionHistoryRepository conversionHistoryRepository;

	@Transactional
	@Scheduled(fixedDelay = 60000)
	public void startAutoConversion() {
		log.info("startAutoConversion started : {}", LocalDateTime.now());
		
		List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
		List<EmployeeCustomerConversion> employeeCustomerConversionList = employeeCustomerConversionRepository.findByReplyByCustomer(false);
		
		for(EmployeeCustomerConversion employeeCustomerConversion : employeeCustomerConversionList) {
			log.info("Convesrion Id : {}, Started time : {}", employeeCustomerConversion.getId(), employeeCustomerConversion.getCreatedAt());
			
			List<ConversionHistory> conversionHistoryList = employeeCustomerConversion.getConversionHistoryList();			
			if(messageTemplateList.size() == conversionHistoryList.size()) {
				log.info("No more message template to send auto message");
				return;
			}
			
			MessageTemplate messageTemplate = messageTemplateList.get(conversionHistoryList.size());
			
			LocalDateTime startTime = employeeCustomerConversion.getCreatedAt().toLocalDateTime();
			LocalDateTime additionalStartTime = startTime.plusMinutes(messageTemplate.getTimeToSend());
			LocalDateTime currentTime = LocalDateTime.now();
			
			if(additionalStartTime.isBefore(currentTime) || additionalStartTime.isEqual(currentTime)) {
				log.info("additionalStartTime isBefore from the currentTime or additionalStartTime is save as current time");
				
				ConversionHistory conversionHistory = new ConversionHistory();
				conversionHistory.setEmployeeCustomerConversion(employeeCustomerConversion);
				conversionHistory.setMessageType("Outgoing");
				conversionHistory.setMessageTemplate(messageTemplate);
				conversionHistory.setMessage(messageTemplate.getMessage().replace("<customer-name>", employeeCustomerConversion.getCustomer().getName()).replace("<employee-name>", employeeCustomerConversion.getEmployee().getName()));
				
				conversionHistoryRepository.save(conversionHistory);
			}		
		}
		
		log.info("startAutoConversion finished : {}", LocalDateTime.now());
	}
}
