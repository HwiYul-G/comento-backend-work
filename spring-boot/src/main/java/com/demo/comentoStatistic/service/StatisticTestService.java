package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.StatisticTestMapper;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticTestService {

    @Autowired
    StatisticTestMapper statisticTestMapper;

    public YearCountDto getYearLogins(String year){
        return statisticTestMapper.selectYearLogin(year);
    }

    public YearMonthCountDto getYearMonthLogins(String year, String month){
        return statisticTestMapper.selectYearMonthLogin(year + month);
    }
}
