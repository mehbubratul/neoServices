package com.mehbub.fraud.service;

import com.mehbub.fraud.entity.FraudCheckHistory;
import com.mehbub.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public Boolean isFraudulentCustomer(Integer customerId){
        boolean isFraudster = false;
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(isFraudster)
                        .createdAt(LocalDateTime.now())
                        .build());
        return isFraudster;
    }
}
