package com.smallB.QOS.statistic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleStatisticResponseDto {
    private Date order_time;
    @PositiveOrZero
    private Long price;
}
