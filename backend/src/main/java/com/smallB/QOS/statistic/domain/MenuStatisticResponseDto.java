package com.smallB.QOS.statistic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuStatisticResponseDto {
    private String product_name;
    private int order_quantity;
}
