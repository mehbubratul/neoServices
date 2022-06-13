package com.mehbub.price.repository;


import com.mehbub.price.entiry.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
