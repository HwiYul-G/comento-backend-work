package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.internal.LoginDeptStatsDto;
import com.demo.comentoStatistic.dto.internal.LoginStatsDto;
import com.demo.comentoStatistic.dto.internal.PostDeptStatsDto;
import com.demo.comentoStatistic.dto.internal.PostStatsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StatisticMapper {

    LoginStatsDto getLoginCountByYearMonth(
            @Param("yearMonth") String yearMonth,
            @Param("excludeHoliday") boolean excludeHoliday
    );

    LoginDeptStatsDto getLoginCountByYearMonthAndTeam(
            @Param("yearMonth") String yearMonth,
            @Param("team") String team
    );

    PostStatsDto getPostCountByYearMonth(String yearMonth);

    PostDeptStatsDto getPostCountByYearMonthAndTeam(
            @Param("yearMonth") String yearMonth,
            @Param("team") String team
    );

}
