package com.smallB.QOS.statistic.service;

import com.smallB.QOS.statistic.domain.SaleStatisticResponseDto;
import com.smallB.QOS.statistic.domain.StatisticRequestDto;
import com.smallB.QOS.statistic.domain.MenuStatisticResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticService {
    List<MenuStatisticResponseDto> getMenuStatistic(StatisticRequestDto menuStatisticRequestDto) throws Exception;
    List<SaleStatisticResponseDto> getSaleStatistic(StatisticRequestDto StatisticRequestDto) throws Exception;
}
