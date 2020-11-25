package com.smallB.QOS.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStateUpdateDto {
    private int order_id;
    private String store_id;
    private int order_type;
    private String order_state;
}
