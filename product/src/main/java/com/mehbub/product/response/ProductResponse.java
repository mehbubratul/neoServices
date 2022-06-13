package com.mehbub.product.response;

import lombok.Builder;

@Builder
public record ProductResponse(
        Integer id,
        String productName,
        String productAliasName,
        Integer productStatus // 0=Inactive ; 1=Active ; 2=Temporarily_off ; 3=Deprecated
) {
}
