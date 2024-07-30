package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.internal.TestYearCountDto;
import com.demo.comentoStatistic.service.StatisticTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("${spring.data.rest.base-path}/")
public class StatisticTestController {
    @Autowired
    StatisticTestService statisticTestService;

    @RequestMapping(value = "/logins/{year}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<TestYearCountDto> getYearLoginCount(@PathVariable("year") String year){
        return ResponseEntity.ok(statisticTestService.getYearLogins(year));
    }

    @RequestMapping(value = "/logins/{year}/{month}", produces = "application/json")
    @ResponseBody
    public Object getYearMonthLoginCount(@PathVariable("year") String year, @PathVariable("month") String month){
        return ResponseEntity.ok(statisticTestService.getYearMonthLogins(year, month));
    }
}
