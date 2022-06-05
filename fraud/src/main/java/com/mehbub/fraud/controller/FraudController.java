package com.mehbub.fraud.controller;

import com.mehbub.fraud.response.FraudCheckResponse;
import com.mehbub.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    public final FraudCheckService fraudCheckService;

    public FraudController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        log.info("Check customer fraudulency : {} ",customerId);
        Boolean isFraudulent= fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulent);
    }

}
