package com.demo.comentoStatistic.dto.external.in;

public record HolidayDto(
        String dateKind,
        String dateName,
        String isHoliday,
        String locdate,
        int seq
) {
}
