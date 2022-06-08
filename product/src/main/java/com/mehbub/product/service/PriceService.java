package com.mehbub.product.service;

import com.mehbub.product.repository.PriceRepository;
import com.mehbub.product.request.PriceSetRequest;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public void setProductPrice(PriceSetRequest priceSetRequest) {

    }
}
