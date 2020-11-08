package com.smallB.QOS.detailOrder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrderDto {
    @Positive
    private int order_id;
    private String store_id;
    private String product_id;
    private int order_quantity;
    private String user_id;
    private String product_name;
    private int product_price;
}
