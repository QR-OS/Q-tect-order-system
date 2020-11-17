package com.smallB.QOS.statistic.controller;

import com.smallB.QOS.statistic.domain.StatisticRequestDto;
import com.smallB.QOS.statistic.service.StatisticService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Date;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.smallB.QOS.statistic.dao")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/statistic/menu/{store_id}")
    public ResponseEntity<?> getMenuStatistic(@PathVariable @Valid String store_id, @RequestParam(value="startDate",required = true)Date startDate, @RequestParam(value="endDate", required = true) Date endDate) throws Exception{
        StatisticRequestDto menuStatisticRequestDto = StatisticRequestDto.builder()
                .store_id(store_id)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        Object res = statisticService.getMenuStatistic(menuStatisticRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/statistic/sale/{store_id}")
    public ResponseEntity<?> getSaleStatistic(@PathVariable @Valid String store_id, @RequestParam(value="startDate",required = true)Date startDate, @RequestParam(value="endDate", required = true) Date endDate) throws Exception{
        StatisticRequestDto statisticRequestDto = StatisticRequestDto.builder()
                .store_id(store_id)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        Object res = statisticService.getSaleStatistic(statisticRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
