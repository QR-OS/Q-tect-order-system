package com.smallB.QOS.statistic.service;

import com.smallB.QOS.statistic.domain.MenuStatisticRequestDto;
import com.smallB.QOS.statistic.domain.MenuStatisticResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticService {
    List<MenuStatisticResponseDto> getMenuStatistic(MenuStatisticRequestDto menuStatisticRequestDto) throws Exception;
}
