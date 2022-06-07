package com.mehbub.product;

import com.mehbub.product.entity.Product;
import com.mehbub.product.repository.ProductRepository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

import java.util.Arrays;

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

    @Before
    @Transactional("productTransactionManager")
    public void setUp() {
        productRepository.save(Product.builder().productName("ABC").productAliasName("abc").productStatus(1).build());
        productRepository.save(Product.builder().productName("DEF").productAliasName("def").productStatus(1).build());
        productRepository.save(Product.builder().productName("IJK").productAliasName("ijk").productStatus(1).build());
        productRepository.save(Product.builder().productName("MNO").productAliasName("mno").productStatus(1).build());
        productRepository.save(Product.builder().productName("XYX").productAliasName("xyz").productStatus(1).build());
    }

    @Test
    public void whenRequestingFirstPageOfSizeTwo_ThenReturnFirstPage() {
        Pageable pageRequest = PageRequest.of(0, 1);

        Page<Product> result = productRepository.findAll(pageRequest);

        assertThat(result.getContent(), hasSize(1));
        assertTrue(result.stream()
                .map(Product::getId)
                .allMatch(id -> Arrays.asList(1001, 1002)
                        .contains(id)));
    }
}
