package com.smallB.QOS.statistic.dao;

import com.smallB.QOS.statistic.domain.MenuStatisticDto;
import com.smallB.QOS.statistic.domain.MenuStatisticRequestDto;
import com.smallB.QOS.statistic.domain.MenuStatisticResponseDto;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface StatisticDao {
    List<MenuStatisticResponseDto> getMenuStatistic(MenuStatisticRequestDto menuStatisticRequestDto) throws Exception;
}
