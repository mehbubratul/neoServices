package com.mehbub.price.service;


import com.mehbub.price.entiry.Price;
import com.mehbub.price.repository.PriceRepository;
import com.mehbub.price.request.PriceSetRequest;
import com.mehbub.price.response.PriceResponse;
import com.mehbub.price.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PriceService {

    private final RestTemplate priceRestTemplate;
    private final PriceRepository priceRepository;

    public void saveProductPrice(PriceSetRequest priceSetRequest) {
        Price price = transformPriceFromPriceSetRequest(priceSetRequest);

        if(!isProductAvailable(priceSetRequest.ProductId())) return;

        if(isProductPriceSetAlready(priceSetRequest.ProductId())) return;;

        // save product price
        priceRepository.save(price);
    }


    public PriceResponse getProductsPrice(Integer productId) {
        Price price = priceRepository.getById(productId);
        PriceResponse productResponse = transformPriceResponseFromPrice(price);
        return productResponse;
    }

    public List<PriceResponse> getProductsPrice() {
        List<Price> priceList = priceRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<PriceResponse> priceResponseList =
                priceList.stream()
                        .map(p -> {
                            PriceResponse pr =
                                    new PriceResponse(
                                            p.getId(),
                                            p.getProductId(),
                                            p.getRate(),
                                            p.getIsDiscountAllowed(),
                                            p.getIsDiscountInPercentage(),
                                            p.getMaxDiscount(),
                                            p.getProductStatus(),
                                            p.getProductPriceEffectiveDate()
                                            );
                            return  pr;
                        })
                        .collect(Collectors.toList());
        return priceResponseList;
    }

    //region private methods

    /**
     * todo: check if product price is already set
     * @param productId
     * @return
     */
    private boolean isProductPriceSetAlready(Integer productId) {
        return false;
    }

    /**
     * todo: check if product is available
     * @param productId
     * @return
     */
    private boolean isProductAvailable(Integer productId) {
        ProductResponse productResponse = getProductResponse(productId);

        if(productResponse != null) return true;

        return true;
    }

    private ProductResponse getProductResponse(Integer productId) {
        String url = "http://PRODUCT/api/v1/product/{productId}";

        ProductResponse productResponse =  priceRestTemplate.getForObject(
                url,
                ProductResponse.class,
                productId
        );
        return productResponse;
    }

    private Price transformPriceFromPriceSetRequest(PriceSetRequest priceSetRequest) {
        Price tempPrice = Price.builder()
                .ProductId(priceSetRequest.ProductId())
                .Rate(priceSetRequest.Rate())
                .isDiscountAllowed(priceSetRequest.isDiscountAllowed())
                .isDiscountInPercentage(priceSetRequest.isDiscountInPercentage())
                .MaxDiscount(priceSetRequest.MaxDiscount())
                .ProductStatus(priceSetRequest.ProductStatus())
                .ProductPriceEffectiveDate(priceSetRequest.ProductPriceEffectiveDate())
                .build();
        return tempPrice;
    }


    private PriceResponse transformPriceResponseFromPrice(Price price) {
        PriceResponse priceResponse = PriceResponse.builder()
                .ProductId(price.getId())
                .ProductId(price.getProductId())
                .Rate(price.getRate())
                .isDiscountAllowed(price.getIsDiscountAllowed())
                .isDiscountInPercentage(price.getIsDiscountInPercentage())
                .MaxDiscount(price.getMaxDiscount())
                .ProductStatus(price.getProductStatus())
                .ProductPriceEffectiveDate(price.getProductPriceEffectiveDate())
                .build();
        return priceResponse;
    }


    //endregion

}
