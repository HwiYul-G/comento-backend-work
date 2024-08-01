package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.StatisticMapper;
import com.demo.comentoStatistic.dto.external.out.LoginDeptStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.LoginStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.PostDeptStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.PostStatsResponseDto;
import com.demo.comentoStatistic.dto.internal.LoginDeptStatsDto;
import com.demo.comentoStatistic.dto.internal.LoginStatsDto;
import com.demo.comentoStatistic.dto.internal.PostDeptStatsDto;
import com.demo.comentoStatistic.dto.internal.PostStatsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticMapper statisticMapper;

    public LoginStatsResponseDto getLoginStats(String yearMonth, boolean isAverage, boolean excludeHoliday) {
        LoginStatsDto loginStatsDto = statisticMapper.getLoginCountByYearMonth(yearMonth, excludeHoliday);
        Optional<Double> average = Optional.empty();
        if (isAverage) {
            average = Optional.of((double) loginStatsDto.totCnt() / getLastDayOfMonth(yearMonth));
            return new LoginStatsResponseDto(true, loginStatsDto.yearMonth(), loginStatsDto.totCnt(), average, loginStatsDto.requestLog());
        }
        return new LoginStatsResponseDto(true, loginStatsDto.yearMonth(), loginStatsDto.totCnt(), average, loginStatsDto.requestLog());
    }


    public LoginDeptStatsResponseDto getLoginDeptStats(String yearMonth, String team) {
        LoginDeptStatsDto loginDeptStatsDto = statisticMapper.getLoginCountByYearMonthAndTeam(yearMonth, team);
        return new LoginDeptStatsResponseDto(true, loginDeptStatsDto.yearMonth(), loginDeptStatsDto.totCnt(), loginDeptStatsDto.requestLog(), loginDeptStatsDto.team());
    }

    public PostStatsResponseDto getPostStats(String yearMonth) {
        PostStatsDto postStatsDto = statisticMapper.getPostCountByYearMonth(yearMonth);
        return new PostStatsResponseDto(true, postStatsDto.yearMonth(), postStatsDto.totCnt(), postStatsDto.requestLog());
    }

    public PostDeptStatsResponseDto getPostDeptStats(String yearMonth, String team) {
        PostDeptStatsDto postDeptStatsDto = statisticMapper.getPostCountByYearMonthAndTeam(yearMonth, team);
        return new PostDeptStatsResponseDto(true, postDeptStatsDto.yearMonth(), postDeptStatsDto.totCnt(), postDeptStatsDto.requestLog(), postDeptStatsDto.team());
    }

    private int getLastDayOfMonth(String yearMonth) {
        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(4));
        LocalDate localDate = LocalDate.of(year, month, 1);
        return localDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
    }

}