package com.mehbub.product.request;

import lombok.Builder;

@Builder
public record ProductRegistrationRequest(
        String ProductName,
        String ProductAliasName,
        Integer ProductStatus
) {

}
