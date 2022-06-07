package com.mehbub.product.service;

import com.mehbub.product.entity.Product;
import com.mehbub.product.repository.ProductRepository;
import com.mehbub.product.request.ProductRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registerProduct(ProductRegistrationRequest productRegistrationRequest) {

        Product product = Product.builder()
                .productName(productRegistrationRequest.ProductName())
                .productAliasName(productRegistrationRequest.ProductAliasName())
                .productStatus(productRegistrationRequest.ProductStatus())
                .build();
        // todo: check if product is valid
        // todo: check if product is not taken

        // save customer
        productRepository.saveAndFlush(product);

        //todo: send notification
    }
}
