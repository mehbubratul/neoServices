package com.mehbub.product.repository;

import com.mehbub.product.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<ProductPrice, Integer> {
}
