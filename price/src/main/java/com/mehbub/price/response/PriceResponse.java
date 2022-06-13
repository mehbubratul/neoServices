package com.mehbub.price.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record PriceResponse(
        Integer id,
        Integer ProductId,
        Double Rate,
        Boolean isDiscountAllowed,
        Boolean isDiscountInPercentage,
        Double MaxDiscount,
        Integer ProductStatus,
        Date ProductPriceEffectiveDate
) {
}
