package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.TestYearCountDto;
import com.demo.comentoStatistic.dto.TestYearMonthCountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticTestMapper {

    TestYearCountDto selectYearLogin(String year);
    TestYearMonthCountDto selectYearMonthLogin(String yearMonth);
}
