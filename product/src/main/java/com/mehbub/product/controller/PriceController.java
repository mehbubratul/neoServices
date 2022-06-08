package com.mehbub.product.controller;

import com.mehbub.product.request.PriceSetRequest;
import com.mehbub.product.request.ProductRegistrationRequest;
import com.mehbub.product.service.PriceService;
import com.mehbub.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    public final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping
    public void setProductPrice(@RequestBody PriceSetRequest priceSetRequest){
        log.info("new price set: {} ", priceSetRequest);
        priceService.setProductPrice(priceSetRequest);
    }



}
