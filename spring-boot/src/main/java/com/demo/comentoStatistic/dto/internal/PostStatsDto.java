package com.demo.comentoStatistic.dto.internal;

public record PostStatsDto(
        String yearMonth,
        Long totCnt,
        String requestLog
) {
}
