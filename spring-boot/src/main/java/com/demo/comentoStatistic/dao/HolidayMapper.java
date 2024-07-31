package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.model.Holiday;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HolidayMapper {

    void insertHoliday(Holiday holiday);

    void insertHolidays(List<Holiday> holidays);
}