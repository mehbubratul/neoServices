package com.mehbub.customer.service;

import com.mehbub.customer.config.CustomerConfig;
import com.mehbub.customer.repository.CustomerRepository;
import com.mehbub.customer.request.CustomerRegistrationRequest;
import com.mehbub.customer.entity.Customer;
import com.mehbub.customer.response.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class CustomerService {

    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        // todo: check if email is valid
        // todo: check if email is not taken

        // save customer
        customerRepository.saveAndFlush(customer);

        // todo: check fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        //todo: send notification
    }
}
