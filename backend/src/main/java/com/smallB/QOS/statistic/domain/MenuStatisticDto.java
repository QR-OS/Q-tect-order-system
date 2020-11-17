package com.smallB.QOS.statistic.domain;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuStatisticDto {
    @NonNull
    private String product_name;
    @PositiveOrZero
    private int Quantity;
}
