package com.smallB.QOS.statistic.service;

import com.smallB.QOS.statistic.dao.StatisticDao;
import com.smallB.QOS.statistic.domain.MenuStatisticResponseDto;
import com.smallB.QOS.statistic.domain.SaleStatisticResponseDto;
import com.smallB.QOS.statistic.domain.StatisticRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private StatisticDao statisticDao;

    @Override
    public List<MenuStatisticResponseDto> getMenuStatistic(StatisticRequestDto menuStatisticRequestDto) throws Exception{
        List<MenuStatisticResponseDto> menuStatisticResponseDtos = statisticDao.getMenuStatistic(menuStatisticRequestDto);
     return menuStatisticResponseDtos;
    }

    @Override
    public List<SaleStatisticResponseDto> getSaleStatistic(StatisticRequestDto statisticRequestDto) throws Exception{
        List<SaleStatisticResponseDto> saleStatisticResponseDtos = statisticDao.getSaleStatistic(statisticRequestDto);
        return saleStatisticResponseDtos;
    }
}
