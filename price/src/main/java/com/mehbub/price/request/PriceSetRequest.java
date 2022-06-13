package com.mehbub.price.request;

import lombok.Builder;

import java.util.Date;

@Builder
public record PriceSetRequest(
        Integer ProductId,
        Double Rate,
        Boolean isDiscountAllowed,
        Boolean isDiscountInPercentage,
        Double MaxDiscount,
        Integer ProductStatus,
        Date ProductPriceEffectiveDate) {

}
