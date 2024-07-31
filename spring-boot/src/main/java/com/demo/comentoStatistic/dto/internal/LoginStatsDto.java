package com.demo.comentoStatistic.dto.internal;

public record LoginStatsDto(
        String yearMonth,
        Long totCnt,
        String requestLog
) {
}
