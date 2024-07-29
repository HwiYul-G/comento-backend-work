package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticTestMapper {

    YearCountDto selectYearLogin(String year);
    YearMonthCountDto selectYearMonthLogin(String yearMonth);
}
