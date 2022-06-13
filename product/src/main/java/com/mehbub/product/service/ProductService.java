package com.mehbub.product.service;

import com.mehbub.product.entity.Product;
import com.mehbub.product.repository.ProductRepository;
import com.mehbub.product.request.ProductRegistrationRequest;
import com.mehbub.product.response.ProductResponse;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //region public methods
    public void registerProduct(ProductRegistrationRequest productRegistrationRequest) {

        Product product = Product.builder()
                .productName(productRegistrationRequest.ProductName())
                .productAliasName(productRegistrationRequest.ProductAliasName())
                .productStatus(productRegistrationRequest.ProductStatus())
                .build();
        // todo: check if product is valid
        // todo: check if product is not taken

        // save product
        productRepository.saveAndFlush(product);

        Integer productId = product.getId();
        // save price for this product
        //todo: send notification
    }

    public ProductResponse getProduct(Integer productId) {
        Product product = productRepository.getById(productId);
        ProductResponse productResponse = transformProductResponseFromProduct(product);
        return productResponse;
    }

    public List<ProductResponse> getProducts() {
        List<Product> productList = productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<ProductResponse> productResponseList =
                productList.stream()
                        .map(p -> {
                            ProductResponse pr =
                                    new ProductResponse(
                                            p.getId(),
                                            p.getProductName(),
                                            p.getProductAliasName(),
                                            p.getProductStatus());
                            return  pr;
                        })
                        .collect(Collectors.toList());
        return productResponseList;
    }
    //endregion

    //region private methods
    private ProductResponse transformProductResponseFromProduct(Product product) {
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productAliasName(product.getProductAliasName())
                .productStatus(product.getProductStatus())
                .build();
        return productResponse;
    }


    //endregion


}
