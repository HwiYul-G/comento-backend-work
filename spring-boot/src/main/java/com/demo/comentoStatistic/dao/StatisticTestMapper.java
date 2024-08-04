package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.internal.TestYearCountDto;
import com.demo.comentoStatistic.dto.internal.TestYearMonthCountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticTestMapper {

    TestYearCountDto selectYearLogin(String year);
    TestYearMonthCountDto selectYearMonthLogin(String yearMonth);
}
