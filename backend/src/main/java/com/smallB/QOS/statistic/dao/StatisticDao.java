package com.smallB.QOS.statistic.dao;

import com.smallB.QOS.statistic.domain.SaleStatisticResponseDto;
import com.smallB.QOS.statistic.domain.StatisticRequestDto;
import com.smallB.QOS.statistic.domain.MenuStatisticResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatisticDao {
    List<MenuStatisticResponseDto> getMenuStatistic(StatisticRequestDto menuStatisticRequestDto) throws Exception;

    List<SaleStatisticResponseDto> getSaleStatistic(StatisticRequestDto StatisticRequestDto) throws Exception;
}
