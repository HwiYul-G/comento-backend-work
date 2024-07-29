package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.StatisticTestMapper;
import com.demo.comentoStatistic.dto.TestYearCountDto;
import com.demo.comentoStatistic.dto.TestYearMonthCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticTestService {

    @Autowired
    StatisticTestMapper statisticTestMapper;

    public TestYearCountDto getYearLogins(String year){
        return statisticTestMapper.selectYearLogin(year);
    }

    public TestYearMonthCountDto getYearMonthLogins(String year, String month){
        return statisticTestMapper.selectYearMonthLogin(year + month);
    }
}
