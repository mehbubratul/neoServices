package com.mehbub.price.controller;


import com.mehbub.price.request.PriceSetRequest;
import com.mehbub.price.response.PriceResponse;
import com.mehbub.price.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        priceService.saveProductPrice(priceSetRequest);
    }

    @GetMapping(path = "{productId}")
    public PriceResponse getProductPrice(@PathVariable("productId") Integer productId){
        log.info("get Price Response  : {} ",productId);
        PriceResponse priceResponse= priceService.getProductsPrice(productId);
        return priceResponse;
    }

    @GetMapping()
    public List<PriceResponse> getProductsPrice(){
        log.info("get Products Price Response  : {} ");
        List<PriceResponse> priceResponseList= priceService.getProductsPrice();
        return priceResponseList;
    }

}
