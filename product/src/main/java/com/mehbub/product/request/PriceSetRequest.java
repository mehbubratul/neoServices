package com.mehbub.product.request;

import java.util.Date;

public record PriceSetRequest(
        Integer ProductId,
        Double Rate,
        Boolean isDiscountAllowed,
        Boolean isDiscountInPercentage,
        Double MaxDiscount,
        Integer ProductStatus,
        Date ProductPriceEffectiveDate) {

}
