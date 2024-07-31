package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.model.Holiday;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HolidayMapper {

    void insertHoliday(Holiday holiday);

}