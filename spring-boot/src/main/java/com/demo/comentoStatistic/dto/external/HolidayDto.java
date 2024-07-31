package com.demo.comentoStatistic.dto.external;

public record HolidayDto(
        String dateKind,
        String dateName,
        String isHoliday,
        String locdate,
        int seq
) {
}
