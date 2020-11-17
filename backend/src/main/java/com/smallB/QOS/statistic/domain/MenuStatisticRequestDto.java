package com.smallB.QOS.statistic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MenuStatisticRequestDto {
    private String store_id;
    private Date startDate;
    private Date endDate;
}
