package com.mehbub.product;

import com.mehbub.product.entity.Product;
import com.mehbub.product.repository.ProductRepository;

import com.mehbub.product.request.ProductRegistrationRequest;
import com.mehbub.product.response.ProductResponse;
import com.mehbub.product.service.ProductService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.Matchers.hasSize;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ProductApplication.class)
@EnableTransactionManagement
public class ProductIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Before
    @Transactional("productTransactionManager")
    public void setUp() {
        productService.registerProduct(ProductRegistrationRequest.builder().ProductName("60 AMS Tola").ProductAliasName("60 Chula").ProductStatus(1).build());
        productService.registerProduct(ProductRegistrationRequest.builder().ProductName("100 AMS Tola").ProductAliasName("100 Chula").ProductStatus(1).build());
        productService.registerProduct(ProductRegistrationRequest.builder().ProductName("30 AMS Changeover").ProductAliasName("30 Changeover").ProductStatus(1).build());
        productService.registerProduct(ProductRegistrationRequest.builder().ProductName("60 AMS Changeover").ProductAliasName("60 Changeover").ProductStatus(1).build());
        productService.registerProduct(ProductRegistrationRequest.builder().ProductName("100 AMS Changeover").ProductAliasName("100 Changeover").ProductStatus(1).build());
    }

    @Test
    public void productListCountIsEqualToProductResponseListCount() {

        List<Product> productList = productRepository.findAll();
        long productListCount = productList.stream().count();

        List<ProductResponse> productResponseList = productService.getProducts();
        long productResponseListCount = productResponseList.stream().count();


        assertTrue(productListCount == productResponseListCount);

    }
}
