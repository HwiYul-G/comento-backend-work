package com.demo.comentoStatistic.dto.external.out;

public record PostStatsResponseDto(
        boolean isSuccess,
        String yearMonth,
        Long totCnt,
        String requestLog
) {
}
