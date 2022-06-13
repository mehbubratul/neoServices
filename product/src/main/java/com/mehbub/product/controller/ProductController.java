package com.mehbub.product.controller;

import com.mehbub.product.request.ProductRegistrationRequest;
import com.mehbub.product.response.ProductResponse;
import com.mehbub.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "{productId}")
    public ProductResponse getProduct(@PathVariable("productId") Integer productId){
        log.info("get Product  : {} ",productId);
        ProductResponse productResponse= productService.getProduct(productId);
        return productResponse;
    }

    @GetMapping()
    public List<ProductResponse> getProducts(){
        log.info("get Products  : {} ");
        List<ProductResponse> productResponseList= productService.getProducts();
        return productResponseList;
    }

}
