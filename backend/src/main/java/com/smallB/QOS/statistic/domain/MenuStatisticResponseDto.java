package com.smallB.QOS.statistic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuStatisticResponseDto {
    private String product_name;
    @PositiveOrZero
    private int order_quantity;
}
