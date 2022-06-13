package com.mehbub.price;

import com.mehbub.price.entiry.Price;
import com.mehbub.price.repository.PriceRepository;
import com.mehbub.price.request.PriceSetRequest;
import com.mehbub.price.response.PriceResponse;
import com.mehbub.price.service.PriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PriceApplication.class)
@EnableTransactionManagement
public class PriceIntegrationTest {

    @Autowired
    private PriceService priceService;

    @Autowired
    private PriceRepository priceRepository;

    @Before
    @Transactional("priceTransactionManager")
    public void setUp() {
        priceService.saveProductPrice(
                PriceSetRequest.builder()
                        .ProductId(1).Rate(24.50).isDiscountAllowed(Boolean.FALSE).isDiscountInPercentage(Boolean.FALSE)
                        .MaxDiscount(0.00).ProductStatus(1).ProductPriceEffectiveDate(new Date()).build());
        priceService.saveProductPrice(
                PriceSetRequest.builder()
                        .ProductId(2).Rate(44.50).isDiscountAllowed(Boolean.FALSE).isDiscountInPercentage(Boolean.FALSE)
                        .MaxDiscount(0.00).ProductStatus(1).ProductPriceEffectiveDate(new Date()).build());
        priceService.saveProductPrice(
                PriceSetRequest.builder()
                        .ProductId(3).Rate(21.00).isDiscountAllowed(Boolean.FALSE).isDiscountInPercentage(Boolean.FALSE)
                        .MaxDiscount(0.00).ProductStatus(1).ProductPriceEffectiveDate(new Date()).build());
        priceService.saveProductPrice(
                PriceSetRequest.builder()
                        .ProductId(4).Rate(24.50).isDiscountAllowed(Boolean.FALSE).isDiscountInPercentage(Boolean.FALSE)
                        .MaxDiscount(0.00).ProductStatus(1).ProductPriceEffectiveDate(new Date()).build());
        priceService.saveProductPrice(
                PriceSetRequest.builder()
                        .ProductId(5).Rate(44.50).isDiscountAllowed(Boolean.FALSE) .isDiscountInPercentage(Boolean.FALSE)
                        .MaxDiscount(0.00).ProductStatus(1).ProductPriceEffectiveDate(new Date()).build());
    }

    @Test
    public void priceListCountIsEqualToPriceResponseListCount() {

        List<Price> priceList = priceRepository.findAll();
        long priceListCount = priceList.stream().count();

        List<PriceResponse> priceResponseList = priceService.getProductsPrice();
        long productResponseListCount = priceResponseList.stream().count();


        assertTrue("priceListCount == productResponseListCount",priceListCount == productResponseListCount);

    }
}
