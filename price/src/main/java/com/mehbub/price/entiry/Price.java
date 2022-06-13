package com.mehbub.price.entiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Price {
    @Id
    @SequenceGenerator(
            name = "product_price_id_sequence",
            sequenceName = "product_price_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_price_id_sequence"
    )
    private Integer id;
    private Integer ProductId;
    private Double Rate;
    private Boolean isDiscountAllowed; // Yes = Discount Allowed; No =  Discount Not Allowed
    private Boolean isDiscountInPercentage; // Yes = in percentage ; No = fixed Amount;
    private Double MaxDiscount; // value either in percentage or fixed ; depends on isDiscountInPercentage
    private Integer ProductStatus; // 0=Inactive ; 1=Active ; 2=Temporarily_off ; 3=Deprecated
    private Date ProductPriceEffectiveDate;
}
