package com.mehbub.product.controller;

import com.mehbub.product.request.ProductRegistrationRequest;
import com.mehbub.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void registerProduct(@RequestBody ProductRegistrationRequest productRegistrationRequest){
        log.info("new product registration: {} ",productRegistrationRequest);
        productService.registerProduct(productRegistrationRequest);
    }



}
