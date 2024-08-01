package com.demo.comentoStatistic.dto.external.in;

import java.util.List;

public record HolidayResponseDto(
        List<HolidayDto> items,
        int numOfRows,
        int pageNo,
        int totalCount
) {
}
