package com.automation.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automation.chat.model.ConversionHistory;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

}
