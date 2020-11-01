package com.smallB.QOS.detailOrder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrderDto {
    private int order_id;
    private String store_id;
    private String product_id;
    private int order_quantity;
    private String user_id;
}
