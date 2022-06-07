package com.mehbub.product.request;

public record ProductRegistrationRequest(
        String ProductName,
        String ProductAliasName,
        Integer ProductStatus
) {

}
